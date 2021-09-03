-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 18-Nov-2020 às 13:04
-- Versão do servidor: 10.4.13-MariaDB
-- versão do PHP: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `bancovendas`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tabela_clientes`
--

CREATE TABLE `tabela_clientes` (
  `idCliente` int(11) NOT NULL,
  `nomeCliente` varchar(50) NOT NULL,
  `enderecoCliente` varchar(50) NOT NULL,
  `bairroCliente` varchar(50) NOT NULL,
  `numeroCliente` varchar(50) NOT NULL,
  `cidadeCliente` varchar(50) NOT NULL,
  `telefoneCliente` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `tabela_clientes`
--

INSERT INTO `tabela_clientes` (`idCliente`, `nomeCliente`, `enderecoCliente`, `bairroCliente`, `numeroCliente`, `cidadeCliente`, `telefoneCliente`) VALUES
(1, 'Guilherme Morais', 'Inacio Novais', 'Salatiel', '189', 'Caratinga', '33 9999-9999'),
(2, 'Ellifas Emanuel', 'Inacio Novais Fonseca', 'Salatiel', '189', 'Caratinga', '33 9 99975899'),
(3, 'João Batista', 'Rua do lixo', 'Salatiel', '250', 'Caratinga', '33 9 8854367'),
(5, 'Guilherme José', 'Alberto Vieira Campos', 'Centro', '7589', 'Caratinga', '33 82828282');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tabela_produto`
--

CREATE TABLE `tabela_produto` (
  `idProduto` int(11) NOT NULL,
  `nomeProduto` varchar(50) NOT NULL,
  `valorProduto` decimal(10,2) NOT NULL,
  `quantidadeProduto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `tabela_produto`
--

INSERT INTO `tabela_produto` (`idProduto`, `nomeProduto`, `valorProduto`, `quantidadeProduto`) VALUES
(1, 'Banana', '1.50', 20),
(2, 'Lapis', '0.05', 10);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tabela_usuarios`
--

CREATE TABLE `tabela_usuarios` (
  `idUsuario` int(11) NOT NULL,
  `nomeUsuario` varchar(50) NOT NULL,
  `loginUsuario` varchar(30) NOT NULL,
  `senhaUsuario` varchar(15) NOT NULL,
  `perfil` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `tabela_usuarios`
--

INSERT INTO `tabela_usuarios` (`idUsuario`, `nomeUsuario`, `loginUsuario`, `senhaUsuario`, `perfil`) VALUES
(1, 'Ellifas Emanuel Bonitao', 'EllifasEmanuel', '30072000', 'user'),
(2, 'Administrador', 'admin', 'admin', 'admin');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `tabela_clientes`
--
ALTER TABLE `tabela_clientes`
  ADD PRIMARY KEY (`idCliente`);

--
-- Índices para tabela `tabela_produto`
--
ALTER TABLE `tabela_produto`
  ADD PRIMARY KEY (`idProduto`);

--
-- Índices para tabela `tabela_usuarios`
--
ALTER TABLE `tabela_usuarios`
  ADD PRIMARY KEY (`idUsuario`),
  ADD UNIQUE KEY `loginUsuario` (`loginUsuario`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `tabela_clientes`
--
ALTER TABLE `tabela_clientes`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `tabela_produto`
--
ALTER TABLE `tabela_produto`
  MODIFY `idProduto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
