const { createApp } = Vue;

createApp({

    data() {
        return {

            isSearchModalActive: false
        };
    },
    methods: {
        searchModalClickHandler()  {
            this.isSearchModalActive = !this.isSearchModalActive
            this.$refs.searchInput.focus()
        }
    },

}).mount("header");
