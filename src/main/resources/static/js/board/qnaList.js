window.addEventListener('load', () => {
    document.querySelector('select[name="categoryId"]').addEventListener('change', function() {
        this.form.submit();
    });
});