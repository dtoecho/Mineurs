const {createApp, ref} = Vue;

import {HexColorPicker} from 'https://unpkg.com/vanilla-colorful?module';


createApp({
    data() {
        return {newColor: {}, newSize: {}}
    },
    mounted() {
        const picker = document.querySelector('hex-color-picker');
        picker.addEventListener('color-changed', (event) => {
            // get updated color value
            this.newColor.hexCode = event.detail.value;
            console.log("this.newColor.hexCode : ", this.newColor.hexCode );

        });
    },
    methods:{
        closeModal(){
            this.$refs.my_modal_2.close();

        }
    }


}).mount("main");


