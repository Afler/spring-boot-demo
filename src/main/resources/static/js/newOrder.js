const order = [{}]

function setUserId(id){

}

document.querySelector('.confirm_button').onclick = async event => {

    const date = new Date()
    //const dateStr = date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear()

    document.querySelectorAll('.detail_row').forEach((row) => {

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
        }
    })

    await getFunc()

    await sendFunc(order).then(response => {
        console.log('send is ok')
    }, error => {
        console.log('no send')
    })
}

async function getFunc() {
    const response = await fetch('http://localhost:8081/rest/getName')
    const text = await response.text()
    console.log(text)
}

async function sendFunc(obj) {
    await fetch('http://localhost:8081/rest/postOrder', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(obj)
    }).then(response => {
        if (response.ok) {
            console.log('response is ok')
        } else {
            console.log('response is bad')
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
                    elem.removeAttribute('hidden')
                })
                return nodeRow.querySelector('.add_to_cart').style.display = 'none'
            case 'remove_from_cart':
                nodeRow.querySelectorAll('.count_manipulation_object').forEach((elem) => {
                    elem.setAttribute('hidden', 'hidden')
                })
                nodeRow.querySelector('.add_to_cart').style.display = 'block'
                return nodeCount.innerHTML = '0'
        }
    }
}

