const {createApp} = Vue;

createApp({
    data() {
        return {
            checkedIds:[]
        };
    },
    methods: {
        async handleDisplayOnClick() {
            patchProducts(this.checkedIds.map(productId=> ({
                id: productId,
                displayed: true
            })))

        },
        async handleDisplayOffClick() {
            patchProducts(this.checkedIds.map(productId=> ({
                id: productId,
                displayed: false
            })))
        },
        async handleSoldOnClick() {
            patchProducts(this.checkedIds.map(productId=> ({
                id: productId,
                sold: true
            })))
        },
        async handleSoldOffClick() {
            patchProducts(this.checkedIds.map(productId=> ({
                id: productId,
                sold: false
            })))
        }
    },
    watch: {
        checkedIds(newValue) {
            console.log("checkedIds newValue:", newValue);
            console.log("checkedIds:", this.checkedIds);
        },
    },


}).mount("main");


async function patchProducts(products) {
    try {
        const response = await fetch('/api/products', {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(products)
            }
        )
        const data = await response.text();
        console.log("data: {}", data);
        window.location.reload()


    } catch (e) {
        console.log("에러남", e)
    }
}
document.addEventListener('DOMContentLoaded', function () {
    var start = document.getElementById('start');
    var end = document.getElementById('end');

    start.addEventListener('change', function () {
        end.min = this.value;
    });
});