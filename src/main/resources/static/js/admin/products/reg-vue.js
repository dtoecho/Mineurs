const {createApp} = Vue;

createApp({
    data() {
        return {
            productItems: [{color: null, colorQuery: "", sizeQuery: "", size: null, qty: 0,  searchedColors:[], searchedSizes:[]}],

        }
    },

    methods: {
        addOption() {
            this.productItems.push({color: null, colorQuery: "", sizeQuery: "", size: null, qty: 0,  searchedColors:[], searchedSizes:[]})
        },
        deleteOption(index) {
            this.productItems.splice(index, 1)

        },
        selectColor(index, color){
            console.log("color: {}", color);
            this.productItems[index].color = color
            console.log("this.productItems[index].color: {}", this.productItems[index].color);
            this.productItems[index].colorQuery=""
        },
        deleteColor(index){
            this.productItems[index].color = null
        },
        selectSize(index, size){
            console.log("size: {}", size);
            this.productItems[index].size = size
            console.log("this.productItems[index].size: {}", this.productItems[index].size);
            this.productItems[index].sizeQuery=""
        },
        deleteSize(index){
            this.productItems[index].size = null
        },

    },
    computed: {
        color() {
            return this.productItems.map(productItem => productItem.colorQuery)
        },
        size() {
            return this.productItems.map(productItem => productItem.sizeQuery)
        },
    },
    watch: {
        color(newVal, oldVal) {
            newVal.forEach((color, index) => {
                if (color === ''){
                    this.productItems[index].searchedColors = []
                }

                if (color !== oldVal[index] && color!=='') {
                    // color 속성이 변경되었을 때 실행할 코드를 작성합니다.
                    // console.log(`인덱스 ${index}의 색상이 변경되었습니다.`);

                    fetch('/api/colors?kor-name='+ color) // 요청할 URL을 입력합니다.
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Network response was not ok');
                            }
                            return response.json(); // JSON 형식으로 응답을 파싱합니다.
                        })
                        .then(data => {
                            // 서버로부터 받은 데이터를 처리합니다.
                            console.log(data);
                            this.productItems[index].searchedColors = data;
                        })
                        .catch(error => {
                            // 오류 처리
                            console.error('There was a problem with the fetch operation:', error);
                        });
                }
            });
        },
        size(newVal, oldVal) {
            newVal.forEach((size, index) => {
                if (size === ''){
                    this.productItems[index].searchedSizes = []
                }

                if (size !== oldVal[index] && size!=='') {
                    // size 속성이 변경되었을 때 실행할 코드를 작성합니다.
                    // console.log(`인덱스 ${index}의 색상이 변경되었습니다.`);

                    fetch('/api/sizes?kor-name='+ size) // 요청할 URL을 입력합니다.
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Network response was not ok');
                            }
                            return response.json(); // JSON 형식으로 응답을 파싱합니다.
                        })
                        .then(data => {
                            // 서버로부터 받은 데이터를 처리합니다.
                            console.log(data);
                            this.productItems[index].searchedSizes = data;
                        })
                        .catch(error => {
                            // 오류 처리
                            console.error('There was a problem with the fetch operation:', error);
                        });
                }
            });
        },
    },

}).mount("main");


