const JAVA="localhost:8080/api/products"
const FLASK="localhost:5000/predict"
document.getElementById("stockForm").onsubmit=async (e) => {
    e.preventDefault();
    await fetch(JAVA,{
        method: "POST",
        headers:{"Content_Type":"application/json"},
        body: JSON.stringify(
            {
            name:document.getElementById("name").value,
            quantity:Number(document.getElementById("quantity").value)
    })
    });
    loadStock();
};
    async function loadStock() {
        const res= await fetch(JAVA);
        const products=await res.json();
        document.getElementById("stockTable").innerHTML=products.map(p => `
            <tr>
               <td>${p.id}</td>

               <td>${p.name}</td>
               <td>${p.quantity}</td>

            </tr>
            `).join("");
        
    }
    async function predictStock() {
        const res=await fetch(FLASK,{
            method: "POST",
            headers:{"Content_Type":"application/json"},
            body:JSON.stringify({
                current_stock:8,
                daily_sales:3
            })

        });
    }
        const data=await res.json();
        document.getElementById("prediction").textContent="predicted Stock"+data.predicted_stock+"|"+data.recommendation;

loadStock();