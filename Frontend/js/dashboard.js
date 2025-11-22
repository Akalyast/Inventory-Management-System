// 🔒 Restrict access if not logged in
if (!localStorage.getItem("isLoggedIn")) {
  window.location.href = "login.html";
}



const productApi = "http://localhost:8085/api/products";
const salesApi = "http://localhost:8085/api/sales";

const totalProductsEl = document.getElementById("totalProducts");
const totalStockEl = document.getElementById("totalStock");
const totalSalesEl = document.getElementById("totalSales");
const lowStockTable = document.querySelector("#lowStockTable tbody");

async function loadDashboard() {
  try {
    const [productRes, salesRes] = await Promise.all([
      fetch(productApi),
      fetch(`${salesApi}/all`)
    ]);

    const products = await productRes.json();
    const sales = await salesRes.json();

    // Total Products
    totalProductsEl.textContent = products.length;

    // Total Stock Quantity
    const totalStock = products.reduce((sum, p) => sum + p.quantity, 0);
    totalStockEl.textContent = totalStock;

    // Total Sales
    const totalSales = sales.reduce((sum, s) => sum + s.quantitySold, 0);
    totalSalesEl.textContent = totalSales;

    // Low Stock Products
    lowStockTable.innerHTML = "";
    products
      .filter(p => p.quantity < 10)
      .forEach(p => {
        const row = document.createElement("tr");
        row.innerHTML = `<td>${p.id}</td><td>${p.name}</td><td>${p.quantity}</td>`;
        lowStockTable.appendChild(row);
      });

    // Draw Sales Trend
    drawSalesChart(sales);

  } catch (err) {
    console.error("Error loading dashboard:", err);
    alert("Failed to load dashboard data!");
  }
}

function drawSalesChart(sales) {
  const ctx = document.getElementById("salesChart").getContext("2d");
  
  const labels = sales.map(s => new Date(s.saleDate).toLocaleDateString());
  const data = sales.map(s => s.quantitySold);

  new Chart(ctx, {
    type: "line",
    data: {
      labels,
      datasets: [
        {
          label: "Sales Quantity",
          data,
          borderColor: "#007bff",
          fill: false,
          tension: 0.3
        }
      ]
    },
    options: {
      responsive: true,
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
}

// Load everything
loadDashboard();
