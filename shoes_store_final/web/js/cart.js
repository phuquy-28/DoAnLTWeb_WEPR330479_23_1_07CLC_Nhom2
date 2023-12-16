const checkboxes = document.querySelectorAll('.itemAmount');
const totalCart = document.getElementById('totalCart');

function updateTotal() {
    let total = 0;
    checkboxes.forEach(checkbox => {
        if (checkbox.checked) {
            total += parseFloat(checkbox.getAttribute('data-amount'));
        }
    });
    totalCart.textContent = `$${total.toFixed(1)}`;
}

checkboxes.forEach(checkbox => {
    checkbox.addEventListener('click', updateTotal);
});
