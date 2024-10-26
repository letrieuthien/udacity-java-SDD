const credentials = $.extend(Repository, {
    add(credential) {
        const isEdit = credential.hasOwnProperty("credentialId") && $.trim(credential.credentialId) !== "";
        const successHandler = (response) => handleResponse(response, '#credentialModal');
        const errorHandler = (response) => handleResponse(response, '#credentialModal');

        isEdit ? this.edit(credential, successHandler, errorHandler) : this.create(credential, successHandler, errorHandler);
    },

    decryptPassword(credential, callback) {
        $.ajax({
            url: `${this.endpoint}/${credential.resourceId}`,
            type: 'GET',
            headers: { 'X-CSRF-TOKEN': credential._csrf },
            timeout: 10000,
            crossDomain: true
        }).done(callback).fail(() => callback("Error decrypting password."));
    },

    remove(credential) {
        this._delete(credential,
            (response) => handleResponse(response, '#credentialModal'),
            (response) => handleResponse(response, '#credentialModal')
        );
    }
});

const handleResponse = (response, modalId) => {
    const $modal = $(modalId);
    const $alertModal = $('#alertModal');

    $modal.trigger("modal:close");
    $alertModal.trigger("response:show", response);
    $('.alert-close').off('click').on('click', () => $('body').trigger("page:reload"));
};

// Modal handlers
function addCredentialModal() {
    $('#credential-id, #credential-url, #credential-username, #credential-password').val('');
    $('#credentialModal').modal('show');
}

function editCredentialModal(credentialId, url, username) {
    const data = {
        _csrf: $('input[name="_csrf"]').val(),
        resourceId: credentialId
    };
    const fragment = $('#credentials-form').attr('action');

    credentials.targetResource(fragment);
    credentials.decryptPassword(data, (decryptedPassword) => {
        $('#credential-password').val(decryptedPassword || '');
    });

    $('#credential-url').val(url || '');
    $('#credential-username').val(username || '');
    $('#credential-id').val(credentialId || '');
    $('#credentialModal').modal('show');
}

function deleteCredential(credentialId) {
    const data = {
        _csrf: $('input[name="_csrf"]').val(),
        resourceId: credentialId
    };
    const fragment = $('#credentials-form').attr('action');

    credentials.targetResource(fragment);
    credentials.remove(data);
}

// Modal and event listeners
$('#credentialModal')
    .on("modal:close", function (event) {
        event.preventDefault();
        $(this).modal('toggle');
    })
    .on("credentials:add", function (event, data, fragment) {
        event.preventDefault();
        credentials.targetResource(fragment);
        credentials.add(data);
    });

$('#save-credential').click(() => {
    const form = $('#credentials-form');
    const data = form.serializeArray();
    const object = data.reduce((obj, item) => ({ ...obj, [item.name]: item.value }), {});

    object._csrf = $('input[name="_csrf"]').val();
    $('#credentialModal').trigger("credentials:add", [object, form.attr('action')]);
});
