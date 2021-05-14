document.querySelector('.confirm_button').onclick = async event => {
    let order = []

    let totalQuantity = 0

    document.querySelectorAll('.detail_row').forEach(row => {

            const quantity = parseInt(row.querySelector('.product_count').innerHTML, 10)

            if (quantity > 0) {

                order.push({
                    detail: {
                        id: 0,
                        quantity: 0,
                        price: 0.0,
                        name: row.querySelector('.detail_name').innerHTML
                    },
                    quantity: quantity,
                    date: '',
                    cost: parseFloat((parseFloat(row.querySelector('.detail_price').innerHTML) * quantity).toFixed(2)),
                    status: 0
                })
                ++totalQuantity
            }

        }
    )

    if (totalQuantity === 0) {
        alert('Пустой заказ')
    } else {

        const username = document.querySelector('.currentUsername').innerHTML

        await sendFunc(order, username).then(response => {
            console.log('send is ok')
        }, error => {
            console.log('no send')
        })

        order = [];
        totalQuantity = 0;
    }
}

async function sendFunc(obj, username) {
    await fetch('http://localhost:8081/rest/postOrder?username=' + username, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(obj)
    }).then(response => {
        if (response.ok) {
            console.log('response is ok')
            alert('Заказ оформлен')
        } else {
            console.log('no response')
        }
    }, error => {
        console.log('no response')
    })
}

document.onclick = event => {

    if (event.target.classList.contains('set_count_button')
        || event.target.classList.contains('add_to_cart')
        || event.target.classList.contains('remove_from_cart')) {
        const targetClass = event.target.classList[0]
        const nodeRow = event.target.parentNode.parentNode
        const nodeCount = nodeRow.querySelector('.product_count')
        let count = parseInt(nodeCount.innerHTML, 10)

        switch (targetClass) {
            case 'set_count_button':
                while (count = prompt('Укажите количество', count)) {
                    if (isNaN(count) || count < 0) {
                        alert("Некорректный ввод")
                    } else {
                        return nodeCount.innerHTML = (parseInt(count, 10)).toString();
                    }
                }
                break
            case 'add_to_cart':
                nodeRow.querySelectorAll('.count_manipulation_object').forEach((elem) => {
                    elem.removeAttribute('style')
                })
                return nodeRow.querySelector('.add_to_cart').style.display = 'none'
            case 'remove_from_cart':
                nodeRow.querySelectorAll('.count_manipulation_object').forEach((elem) => {
                    elem.setAttribute('style', 'display: none')
                })
                nodeRow.querySelector('.add_to_cart').style.display = 'block'
                return nodeCount.innerHTML = '0'
        }
    }
}

