function baseurl(fragment) {
    const loc = window.location;
    return loc.origin + fragment;
}

$(document).ready(function(){
    const _location = window.location;
    $('.nav-tabs a[href="' + _location.hash + '"]').tab('show');
});

$('.nav-link').click(function (event) {
    event.preventDefault();
    window.location.hash = $(event.target).attr("href");
});

$('#alertModal').on("response:show", function (event, errorPage) {
    event.preventDefault();
    event.stopPropagation();
    let html = $(errorPage);
    let message = html.filter('#resultMessageContent').html();

    $('#alertModalBody').html(message);
    $('#alertModal').modal('show');
});

$('body').on("page:reload", function (event) {
    event.preventDefault();
    event.stopPropagation();
    $(this).fadeOut(500, function(){
        location.reload();
    });
});

const handleResponse = (response, modalId, onDelete = false) => {
    if(!onDelete) {
        const $modal = $(modalId);
        $modal.trigger("modal:close");
    }
    const $alertModal = $('#alertModal');
    $alertModal.trigger("response:show", response);
    $('.alert-close').off('click').on('click', () => $('body').trigger("page:reload"));
};