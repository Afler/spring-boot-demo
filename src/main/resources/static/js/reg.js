const sellerSelectorDiv = document.querySelector('.sellerSelectorDiv')
const roleSelector = document.querySelector('.roleSelector')

if (sellerSelectorDiv.lastElementChild.value === "") {
    roleSelector.removeChild(roleSelector.firstElementChild);
    sellerSelectorDiv.setAttribute('hidden', 'hidden')
} else {
    roleSelector.addEventListener('change', function () {
        if (roleSelector.value === "SELLER") {
            sellerSelectorDiv.setAttribute('hidden', 'hidden')
        } else {
            sellerSelectorDiv.removeAttribute('hidden')
        }
    })
}
