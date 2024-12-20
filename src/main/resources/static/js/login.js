document.getElementById("loginForm").addEventListener("submit", function (event) {
    event.preventDefault(); // Impede o envio padrão do formulário

    const login = document.getElementById("login").value.trim();
    const senha = document.getElementById("senha").value.trim();

    if (login === "admin" && senha === "admin") {
        // Login do administrador
        window.location.href = "/menuAdmin";
    } else if (validarCliente(login, senha)) {
        // Login de cliente
        window.location.href = "/indexCliente";
    } else {
        // Credenciais inválidas
        alert("Login ou senha inválidos. Tente novamente.");
    }
});
