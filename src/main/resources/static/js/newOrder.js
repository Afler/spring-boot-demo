document.onclick = event => {

    if (event.target.classList.contains('plus_button')
        || event.target.classList.contains('minus_button')
        || event.target.classList.contains('set_count_button')
        || event.target.classList.contains('add_to_cart')
        || event.target.classList.contains('remove_from_cart')) {
        let targetClass = event.target.classList[0];
        let nodeRow = event.target.parentNode.parentNode;
        let nodeCount = nodeRow.querySelector('.product_count')
        let count = parseInt(nodeCount.innerHTML, 10)

        switch (targetClass) {
            case 'plus_button':
                console.log('plus')
                return nodeCount.innerHTML = ++count
            case 'minus_button':
                console.log('minus')
                if (count > 0)
                    return nodeCount.innerHTML = --count
                else return nodeCount.innerHTML = count
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
                return nodeRow.querySelector('.add_to_cart').style.display = 'block'
        }
    }
}

