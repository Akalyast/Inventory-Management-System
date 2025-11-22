// 🔒 Restrict access if not logged in
if (!localStorage.getItem("isLoggedIn")) {
  window.location.href = "login.html";
}


const productApi = "http://localhost:8085/api/products";
const stockApi = "http://localhost:8085/api/stock";

const tableBody = document.querySelector("#productTable tbody");
const updateSection = document.getElementById("updateSection");
const selectedProductName = document.getElementById("selectedProductName");
const addQuantityInput = document.getElementById("addQuantity");
const confirmAddButton = document.getElementById("confirmAdd");

let selectedProductId = null;

// Fetch all products
async function loadProducts() {
  try {
    const res = await fetch(productApi);
    const products = await res.json();
    tableBody.innerHTML = "";

    products.forEach((p) => {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${p.id}</td>
        <td>${p.name}</td>
        <td>${p.category}</td>
        <td>${p.price}</td>
        <td>${p.quantity}</td>
        <td><button onclick="openUpdate(${p.id}, '${p.name}')">➕ Add Stock</button></td>
      `;
      tableBody.appendChild(row);
    });
  } catch (err) {
    console.error("Error loading products:", err);
    alert("Failed to load products!");
  }
}

// Open update section
function openUpdate(id, name) {
  selectedProductId = id;
  selectedProductName.textContent = name;
  updateSection.style.display = "block";
}

// Add stock
confirmAddButton.addEventListener("click", async () => {
  const quantity = parseInt(addQuantityInput.value);
  if (!quantity || quantity <= 0) {
    alert("Enter a valid quantity!");
    return;
  }

  try {
    const res = await fetch(`${stockApi}/add/${selectedProductId}?quantityAdded=${quantity}`, {
  method: "POST"
});



    if (res.ok) {
      alert("✅ Stock updated successfully!");
      updateSection.style.display = "none";
      addQuantityInput.value = "";
      loadProducts(); // Refresh table
    } else {
      const errText = await res.text();
      console.error("Error updating stock:", errText);
      alert("❌ Failed to update stock!");
    }
  } catch (error) {
    console.error("Fetch error:", error);
    alert("❌ Could not connect to backend!");
  }
});

// Initial load
loadProducts();
