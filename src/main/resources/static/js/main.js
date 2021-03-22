//alert("JS ACTIVATED")

const sellerSelector = document.querySelector('.sellerSelector')
const roleSelector = document.querySelector('.roleSelector')


roleSelector.addEventListener('change', function () {
    if (sellerSelector.getAttribute('hidden') == null
    ) {
        sellerSelector.setAttribute('hidden', 'hidden')
    } else {
        sellerSelector.removeAttribute('hidden')
    }
})
