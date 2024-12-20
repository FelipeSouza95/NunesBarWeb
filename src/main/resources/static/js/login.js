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

// Função para validar login e senha de cliente
function validarCliente(login, senha) {
    // Aqui você pode implementar a validação com base em uma lista de clientes ou API.
    // Exemplo de validação estática para demonstração:
    const clientes = [
        { cpf: "12345678901", senha: "senha123" },
        { cpf: "09876543210", senha: "senha456" }
    ];

    return clientes.some(cliente => cliente.cpf === login && cliente.senha === senha);
}
