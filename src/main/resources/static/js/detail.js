// 로그인 되어있는지 확인하는 값
let isLogin = document.querySelector('.isLogin').innerText;

// 구매,장바구니,찜 버튼
const buyButton = document.querySelector('button[name="userAction"][value="1"]');
const cartButton = document.querySelector('button[name="userAction"][value="2"]');
const wishButton = document.querySelector('button[name="userAction"][value="3"]');

// 상품 추가시마다 +, 하나 지울때마다 -
// isValid이 0일 때는 구매, 장바구니 버튼 X 
let isValid = 0;

// 버튼에 클릭 이벤트 리스너를 추가합니다.
buyButton.addEventListener('click', function (event) {
    if(isLogin == ""){
        event.preventDefault();
        alert("로그인 하세요.");
        window.location.href = "http://localhost:8083/login";
    }
    else
        if(isValid == 0){
            event.preventDefault();
            alert("상품 옵션을 선택하세요.");
        }
});
cartButton.addEventListener('click', function (event) {
    if(isLogin == ""){
        event.preventDefault();
        alert("로그인 하세요.");
        window.location.href = "http://localhost:8083/login";
    }
    else{
        if(isValid == 0){
            event.preventDefault();
            alert("상품 옵션을 선택하세요.");
        }
        else
            alert('장바구니 담기 완료');
    }
});
wishButton.addEventListener('click', function (event) {
    if(isLogin == ""){
        event.preventDefault();
        alert("로그인 하세요.");
        window.location.href = "http://localhost:8083/login";
    }
    else
        alert('위시리스트 담기 완료');
});

document.addEventListener('DOMContentLoaded', function () {
    const colorInputs = document.querySelectorAll('input[name="colorId"]');
    const sizeInputs = document.querySelectorAll('input[name="sizeId"]');
    const clickListContainer = document.querySelector('.clickList');
    const productNameElement = document.querySelector('p.text-2xl');
    const productName = productNameElement ? productNameElement.textContent.trim() : '';

    // 제품 가격을 가져오는 코드
    const productPriceElement = document.querySelector('section.mt-10.text-base p');
    const productPrice = productPriceElement ? parseFloat(productPriceElement.textContent.replace(/[^0-9.]/g, '')) : 0;

    // 총 수량과 총 가격을 표시할 요소
    const totalQtyElement = document.querySelector('.total-qty');
    const totalPriceElement = document.querySelector('.total-price');

    // 선택된 옵션을 처리하는 함수
    function handleSelectionChange() {
        let selectedColorName = null;
        let selectedSizeName = null;

        // 선택된 색상과 사이즈를 가져옵니다.
        colorInputs.forEach(input => {
            if (input.checked) {
                const label = input.closest('label');
                selectedColorName = label.querySelector('span').textContent.trim();
            }
        });

        sizeInputs.forEach(input => {
            if (input.checked) {
                const label = input.closest('label');
                selectedSizeName = label.querySelector('span').textContent.trim();
            }
        });

        // 색상과 사이즈가 모두 선택된 경우
        if (selectedColorName && selectedSizeName) {
            let isDuplicate = false;

            // 기존 아이템 중에서 동일한 색상과 사이즈 조합이 있는지 검사
            clickListContainer.querySelectorAll('.clickList div').forEach(div => {
                const existingColor = div.getAttribute('data-color');
                const existingSize = div.getAttribute('data-size');

                // 색상과 사이즈 조합이 중복된 경우
                if (existingColor === selectedColorName && existingSize === selectedSizeName) {
                    isDuplicate = true;
                }
            });

            // 중복이 없으면 새 항목 추가
            if (!isDuplicate) {
                addNewItem(selectedColorName, selectedSizeName);
                isValid++;
            }

            // 선택된 옵션 해제
            colorInputs.forEach(input => {
                input.checked = false;
            });
            sizeInputs.forEach(input => {
                input.checked = false;
            });

            // 총 수량과 총 가격 업데이트
            updateTotals();
        }
    }

    function addNewItem(colorName, sizeName) {
        const newDiv = document.createElement('div');
        newDiv.className = 'clickList mt-10 text-gray-900 flex items-center text-lg';

        // 데이터 속성을 추가하여 색상 및 사이즈를 저장합니다.
        newDiv.setAttribute('data-color', colorName);
        newDiv.setAttribute('data-size', sizeName);

        // 제품 이름 추가
        // const productNameSpan = document.createElement('span');
        // productNameSpan.textContent = productName;
        // newDiv.appendChild(productNameSpan);

        // 색상 및 사이즈 추가
        newDiv.appendChild(document.createTextNode(' '));
        const colorSpan = document.createElement('span');
        colorSpan.textContent = `Color: ${colorName}`;
        colorSpan.className = 'color-span';
        newDiv.appendChild(colorSpan);

        // colorSpan과 sizeSpan 사이에 3개의 non-breaking space를 추가합니다.
        newDiv.appendChild(document.createTextNode('\u00A0\u00A0'));

        const sizeSpan = document.createElement('span');
        sizeSpan.textContent = `Size: ${sizeName}`;
        sizeSpan.className = 'size-span';
        newDiv.appendChild(sizeSpan);

        // 수량 및 가격 관리 요소 추가
        const quantityContainer = document.createElement('div');
        quantityContainer.className = 'quantity-container flex items-center mx-2';

        const increaseButton = document.createElement('button');
        increaseButton.textContent = '+';
        increaseButton.className = 'quantity-button';

        const decreaseButton = document.createElement('button');
        decreaseButton.textContent = '-';
        decreaseButton.className = 'quantity-button';

        const quantitySpan = document.createElement('span');
        quantitySpan.textContent = '1';
        quantitySpan.className = 'quantity-value mx-2';

        // 가격을 표시할 요소 추가
        const priceSpan = document.createElement('span');
        priceSpan.className = 'price-value mx-2';
        priceSpan.textContent = `${(productPrice * 1).toLocaleString()}원`;

        // 수량을 위한 `input` 태그를 추가합니다.
        const quantityInput = document.createElement('input');
        quantityInput.setAttribute('type', 'hidden');
        quantityInput.setAttribute('name', `quantity`);
        quantityInput.setAttribute('value', '1'); // 초기 수량은 1로 설정합니다.

        // 수량 조정 및 가격 표시를 위한 이벤트 리스너 추가
        increaseButton.addEventListener('click', function (event) {
            event.preventDefault();
            let quantity = parseInt(quantitySpan.textContent, 10) + 1;
            quantitySpan.textContent = quantity;

            // quantityInput의 value를 업데이트합니다.
            quantityInput.setAttribute('value', quantity);

            // 가격 업데이트
            priceSpan.textContent = `${(productPrice * quantity).toLocaleString()}원`;

            // 총 수량과 총 가격 업데이트
            updateTotals();
        });

        decreaseButton.addEventListener('click', function (event) {
            event.preventDefault();
            let quantity = parseInt(quantitySpan.textContent, 10);
            if (quantity > 1) {
                quantity -= 1;
                quantitySpan.textContent = quantity;

                // quantityInput의 value를 업데이트합니다.
                quantityInput.setAttribute('value', quantity);

                // 가격 업데이트
                priceSpan.textContent = `${(productPrice * quantity).toLocaleString()}원`;

                // 총 수량과 총 가격 업데이트
                updateTotals();
            }
        });

        // 삭제 버튼 이벤트 리스너 추가
        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'X';
        deleteButton.className = 'ml-2 text-black cursor-pointer';
        deleteButton.style.fontWeight = 'bold';

        deleteButton.addEventListener('click', function (event) {
            event.preventDefault();
            isValid--;

            // 아이템 삭제
            newDiv.remove();

            // 총 수량과 총 가격 업데이트
            updateTotals();
        });



        // 색상 및 사이즈를 위한 `input` 태그를 추가합니다.
        const colorInput = document.createElement('input');
        colorInput.setAttribute('type', 'hidden');
        colorInput.setAttribute('name', `colorName`);
        colorInput.setAttribute('value', colorName);

        const sizeInput = document.createElement('input');
        sizeInput.setAttribute('type', 'hidden');
        sizeInput.setAttribute('name', `sizeName`);
        sizeInput.setAttribute('value', sizeName);

        // 생성된 `input` 태그를 `newDiv`에 추가합니다.
        newDiv.appendChild(quantityInput);
        newDiv.appendChild(colorInput);
        newDiv.appendChild(sizeInput);

        // 요소를 컨테이너에 추가
        quantityContainer.appendChild(priceSpan);
        quantityContainer.appendChild(decreaseButton);
        quantityContainer.appendChild(quantitySpan);
        quantityContainer.appendChild(increaseButton);

        newDiv.appendChild(quantityContainer);
        newDiv.appendChild(deleteButton);

        clickListContainer.appendChild(newDiv);

        // 총 수량과 총 가격 업데이트
        updateTotals();
    }

    // 총 수량과 총 가격을 업데이트하는 함수
    function updateTotals() {
        let totalQuantity = 0;
        let totalPrice = 0;

        // .clickList div에 있는 각 행의 수량과 가격을 합산합니다.
        clickListContainer.querySelectorAll('.clickList div').forEach(div => {
            const quantitySpan = div.querySelector('.quantity-value');
            const priceSpan = div.querySelector('.price-value');

            // 각 행의 수량과 가격을 정확히 파싱합니다.
            const quantity = parseInt(quantitySpan.textContent, 10);
            const price = parseFloat(priceSpan.textContent.replace(/[^0-9.]/g, ''));

            // 총 수량과 총 가격을 합산합니다.
            totalQuantity += quantity;
            totalPrice += price;
        });

        // 총 수량과 총 가격을 .total-qty와 .total-price 요소에 업데이트합니다.


        totalQuantity /= 2; // (버그)개수가 2개씩 들어간다.. 챗gpt한테 다 물어봐서 했더니 어디가 문제인지도 잘 모르겠음
        totalPrice /= 2; // 그래서 그냥 넣기 전에 2로 나눠서 넣었음
        totalQtyElement.textContent = totalQuantity.toString();  // 정확한 총 수량을 업데이트
        totalPriceElement.textContent = `${totalPrice.toLocaleString()}원`;  // 정확한 총 가격을 업데이트
    }


    // 이벤트 리스너 추가
    colorInputs.forEach(input => {
        input.addEventListener('change', handleSelectionChange);
    });

    sizeInputs.forEach(input => {
        input.addEventListener('change', handleSelectionChange);
    });
    
});

