// 🔒 Restrict access if not logged in
if (!localStorage.getItem("isLoggedIn")) {
  window.location.href = "login.html";
}


const productApi = "http://localhost:8085/api/products";
const salesApi = "http://localhost:8085/api/sales";

const productTable = document.querySelector("#productTable tbody");
const salesTable = document.querySelector("#salesTable tbody");
const saleSection = document.getElementById("saleSection");
const selectedProductName = document.getElementById("selectedProductName");
const saleQuantityInput = document.getElementById("saleQuantity");
const confirmSaleButton = document.getElementById("confirmSale");

let selectedProductId = null;

// Load products
async function loadProducts() {
  const res = await fetch(productApi);
  const products = await res.json();

  productTable.innerHTML = "";
  products.forEach(p => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${p.id}</td>
      <td>${p.name}</td>
      <td>${p.category}</td>
      <td>${p.price}</td>
      <td>${p.quantity}</td>
      <td><button onclick="openSale(${p.id}, '${p.name}')">🛒 Sell</button></td>
    `;
    productTable.appendChild(row);
  });
}

// Open sale section
function openSale(id, name) {
  selectedProductId = id;
  selectedProductName.textContent = name;
  saleSection.style.display = "block";
}

// Record sale
confirmSaleButton.addEventListener("click", async () => {
  const quantity = parseInt(saleQuantityInput.value);
  if (!quantity || quantity <= 0) {
    alert("Enter valid quantity!");
    return;
  }

  try {
    const res = await fetch(`${salesApi}/record/${selectedProductId}?quantitySold=${quantity}`, {
      method: "POST"
    });

    if (res.ok) {
      alert("✅ Sale recorded successfully!");
      saleSection.style.display = "none";
      saleQuantityInput.value = "";
      loadProducts();
      loadSales();
    } else {
      alert("❌ Failed to record sale!");
    }
  } catch (err) {
    console.error(err);
    alert("⚠️ Could not connect to backend!");
  }
});

// Load sales history
async function loadSales() {
  const res = await fetch(`${salesApi}/all`);
  const sales = await res.json();

  salesTable.innerHTML = "";
  sales.forEach(s => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${s.id}</td>
      <td>${s.product.name}</td>
      <td>${s.quantitySold}</td>
      <td>${s.saleDate}</td>
    `;
    salesTable.appendChild(row);
  });
}

function exportSalesToCSV() {
  fetch("http://localhost:8085/api/sales/all")
    .then(res => res.json())
    .then(sales => {
      let csvContent = "data:text/csv;charset=utf-8,";
      csvContent += "ID,Product,Quantity Sold,Date\n";

      sales.forEach(s => {
        csvContent += `${s.id},${s.product.name},${s.quantitySold},${s.saleDate}\n`;
      });

      const encodedUri = encodeURI(csvContent);
      const link = document.createElement("a");
      link.setAttribute("href", encodedUri);
      link.setAttribute("download", "sales_report.csv");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    })
    .catch(err => alert("Failed to export sales: " + err));
}

// Initial load
loadProducts();
loadSales();
