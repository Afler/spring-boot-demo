const sellerSelector = document.querySelector('.sellerSelector')
const roleSelector = document.querySelector('.roleSelector')

roleSelector.addEventListener('change', function () {
    if (roleSelector.value === "SELLER") {
        sellerSelector.setAttribute('hidden', 'hidden')
    } else {
        sellerSelector.removeAttribute('hidden')
    }
})
