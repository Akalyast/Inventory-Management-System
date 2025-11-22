// 🔒 Restrict access if not logged in
if (!localStorage.getItem("isLoggedIn")) {
  window.location.href = "login.html";
}


const apiBase = "http://localhost:8085/api/products"; // ✅ Make sure this matches your backend port

// Load all products
async function loadProducts() {
  try {
    const response = await fetch(apiBase);
    const products = await response.json();

    const tableBody = document.querySelector("#productTable tbody");
    tableBody.innerHTML = "";

    products.forEach(p => {
      const row = `
        <tr>
          <td>${p.id}</td>
          <td>${p.name}</td>
          <td>${p.category}</td>
          <td>₹${p.price}</td>
          <td>${p.quantity}</td>
        </tr>
      `;
      tableBody.innerHTML += row;
    });
  } catch (error) {
    console.error("Error loading products:", error);
    alert("❌ Could not load products!");
  }
}

// Add a new product
document.getElementById("addProductForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const product = {
    name: document.getElementById("name").value,
    category: document.getElementById("category").value,
    price: parseFloat(document.getElementById("price").value),
    quantity: parseInt(document.getElementById("quantity").value)
  };

  console.log("Sending product:", product);

  try {
    const response = await fetch(apiBase, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(product)
    });

    console.log("Response status:", response.status);

    if (response.ok) {
      alert("✅ Product added successfully!");
      document.getElementById("addProductForm").reset();
      loadProducts();
    } else {
      const errorText = await response.text();
      console.error("Server error:", errorText);
      alert("❌ Failed to add product!");
    }
  } catch (error) {
    console.error("Fetch error:", error);
    alert("⚠️ Unable to connect to backend!");
  }
});

function exportProductsToCSV() {
  fetch("http://localhost:8085/api/products")
    .then(res => res.json())
    .then(products => {
      let csvContent = "data:text/csv;charset=utf-8,";
      csvContent += "ID,Name,Category,Price,Quantity\n";

      products.forEach(p => {
        csvContent += `${p.id},${p.name},${p.category},${p.price},${p.quantity}\n`;
      });

      const encodedUri = encodeURI(csvContent);
      const link = document.createElement("a");
      link.setAttribute("href", encodedUri);
      link.setAttribute("download", "products_report.csv");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    })
    .catch(err => alert("Failed to export products: " + err));
}

window.onload = loadProducts;
