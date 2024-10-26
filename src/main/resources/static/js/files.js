const Files = (fragment) => {
    const endpoint = baseurl(fragment);
    const $alertModal = $('#alertModal');
    const $body = $('body');

    const handleResponse = (response) => {
        $alertModal.trigger("response:show", response);
        $alertModal.on('hidden.bs.modal', () => $body.trigger("page:reload"));
        $('.alert-close').click(() => $body.trigger("page:reload"));
    };

    const add = (data) => {
        $.ajax({
            url: endpoint,
            type: 'POST',
            cache: false,
            crossDomain: true,
            processData: false,
            contentType: false,
            enctype: 'multipart/form-data',
            data: new FormData(data.form),
            headers: { 'X-CSRF-TOKEN': data._csrf },
            success: handleResponse,
            error: (xhr) => console.error("Add file request failed", xhr)
        });
    };

    const remove = (data) => {
        $.ajax({
            url: `${endpoint}/${data.fileId}`,
            type: 'DELETE',
            headers: { 'X-CSRF-TOKEN': data._csrf },
            timeout: 10000,
            crossDomain: true,
            success: handleResponse,
            error: (xhr) => $alertModal.trigger("response:show", xhr.responseText)
        });
    };

    return {
        store: add,
        remove
    };
};

const deleteFile = (fileId) => {
    const data = {
        _csrf: $('input[name="_csrf"]').val(),
        fileId
    };
    const fragment = $('#files-form').attr('action');
    Files(fragment).remove(data);
};

$('#files-form').on("files:add", (event, data, fragment) => {
    event.preventDefault();
    Files(fragment).store(data);
});

$('#upload-file').click(() => {
    const $form = $('#files-form');
    const data = {
        _csrf: $('input[name="_csrf"]').val(),
        form: $form[0]
    };
    $form.trigger("files:add", [data, $form.attr('action')]);
});
