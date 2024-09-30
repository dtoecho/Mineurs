$(document).ready(function() {
    $('#summernote').summernote({
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'italic', 'underline', 'clear']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            // ['insert', ['link', 'picture', 'video']],
        ],
        height: 300,
        minHeight: null,
        maxHeight: null,
        focus: true
    });
});