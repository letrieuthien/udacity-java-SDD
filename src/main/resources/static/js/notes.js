const notes = $.extend(Repository, {
    add(note) {
        const isEdit = note.hasOwnProperty("noteId") && $.trim(note.noteId) !== "";
        const successHandler = (response) => handleModalResponse(response, '#noteModal');
        const errorHandler = (response) => handleModalResponse(response, '#noteModal');
        isEdit ? this.edit(note, successHandler, errorHandler) : this.create(note, successHandler, errorHandler);
    },

    remove(note) {
        this._delete(note,
            (response) => handleResponse(response, '#noteModal'),
            (response) => handleResponse(response, '#noteModal')
        );
    }
});

const handleModalResponse = (response, modalId, reloadOnClose = false) => {
    const $modal = $(modalId);
    const $alertModal = $('#alertModal');

    $modal.trigger("modal:close");
    $alertModal.trigger("response:show", response);

    $('.alert-close').off('click').on('click', () => $('body').trigger("page:reload"));
};

// Modal handlers
function addNoteModal() {
    $('#note-id, #note-title, #note-description').val('');
    $('#noteModal').modal('show');
}

function editNoteModal(noteId, noteTitle, noteDescription) {

    const data = {
        _csrf: $('input[name="_csrf"]').val(),
        resourceId: noteId
    };
    const fragment = $('#notes-form').attr('action');

    notes.targetResource(fragment);
    $('#note-title').val(noteTitle || '');
    $('#note-description').val(noteDescription || '');
    $('#note-id').val(noteId || '');
    $('#noteModal').modal('show');
}

function deleteNote(noteId) {
    const data = {
        _csrf: $('input[name="_csrf"]').val(),
        resourceId: noteId
    };
    const fragment = $('#notes-form').attr('action');

    notes.targetResource(fragment);
    notes.remove(data);
}

$('#noteModal')
    .on("modal:close", function (event) {
        event.preventDefault();
        $(this).modal('toggle');
    })
    .on("notes:add", function (event, data, fragment)  {
        if (!data.noteTitle.trim()) return;  // Only add if title is present
        event.preventDefault();
        notes.targetResource(fragment);
        console.log(data)
        notes.add(data);
    });

$('#save-note').click(() => {
    const form = $('#notes-form');
    const data = form.serializeArray();
    const object = data.reduce((obj, item) => ({ ...obj, [item.name]: item.value }), {});

    object._csrf = $('input[name="_csrf"]').val();
    $('#noteModal').trigger("notes:add", [object, form.attr('action')]);
});
