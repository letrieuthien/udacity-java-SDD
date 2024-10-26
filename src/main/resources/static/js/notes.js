const NoteRepository = {
    targetResource: function (fragment) {
        this.endpoint = baseurl(fragment);
    },
    create: function (data, successCallback, failureCallback) {

        $.ajax({
            url: this.endpoint,
            type: 'post',
            crossDomain: true,
            data: $.param(data),
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            timeout: 10000,
            headers: {
                'X-CSRF-TOKEN': data._csrf,
            }

        }).done(function (response) {
            successCallback(response);

        }).fail(function (xhr) {
            failureCallback(xhr.responseText);

        });

    },
    edit: function (data, successCallback, failureCallback) {
        console.log(`${this.endpoint}/${data.resourceId}`)

        $.ajax({
            url: `${this.endpoint}/${data.resourceId}`,
            type: 'put',
            beforeSend: function () {
            },
            data: $.param(data),
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            timeout: 10000,
            headers: {
                'X-CSRF-TOKEN': data._csrf
            }

        }).done(function (response) {
            successCallback(response);

        }).fail(function (xhr) {
            failureCallback(xhr.responseText);

        });

    },
    _delete: function (data, successCallback, failureCallback) {

        $.ajax({
            url: `${this.endpoint}/${data.resourceId}`,
            type: 'delete',
            headers: {
                'X-CSRF-TOKEN': data._csrf,
            },
            timeout: 10000,
            crossDomain: true,

        }).done(function (response) {
            successCallback(response);

        }).fail(function (xhr) {
            failureCallback(xhr.responseText);

        });

    }
}
const notes = $.extend(NoteRepository, {
    add(note) {
        const isEdit = note.hasOwnProperty("noteId") && $.trim(note.noteId) !== "";
        const successHandler = (response) => handleResponse(response, '#noteModal');
        const errorHandler = (response) => handleResponse(response, '#noteModal');
        isEdit ? this.edit(note, successHandler, errorHandler) : this.create(note, successHandler, errorHandler);
    },
    remove(note) {
        this._delete(note,
            (response) => handleResponse(response, '#noteModal', true),
            (response) => handleResponse(response, '#noteModal', true)
        );
    }
});
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
    .on("notes:add", function (event, data, fragment) {
        if (!data.noteTitle.trim()) return;  // Only add if title is present
        event.preventDefault();
        notes.targetResource(fragment);
        notes.add(data);
    });

$('#save-note').click(() => {
    const form = $('#notes-form');
    const data = form.serializeArray();
    const object = data.reduce((obj, item) => ({...obj, [item.name]: item.value}), {});

    object._csrf = $('input[name="_csrf"]').val();
    $('#noteModal').trigger("notes:add", [object, form.attr('action')]);
});
