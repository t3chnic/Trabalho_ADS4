USE [Ecommerce]
GO
/****** Object:  Table [dbo].[Cliente]    Script Date: 05/25/2015 19:58:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Cliente](
	[cliente_id] [int] NOT NULL,
	[cliente_nome] [varchar](50) NULL,
	[cliente_dtnasc] [date] NULL,
	[cliente_cpf] [varchar](14) NULL,
	[cliente_telefone] [varchar](14) NULL,
	[cliente_endereco] [varchar](50) NULL,
	[cliente_email] [varchar](30) NULL,
	[cliente_senha] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[cliente_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Cliente] ([cliente_id], [cliente_nome], [cliente_dtnasc], [cliente_cpf], [cliente_telefone], [cliente_endereco], [cliente_email], [cliente_senha]) VALUES (1, N'Maria Diva Melo Guimaraes', CAST(0xFFFF0A00 AS Date), N'948.749.823-74', N'(47) 8397-4893', N'rua tal', N'diva@diva.com.br', N'diva')
INSERT [dbo].[Cliente] ([cliente_id], [cliente_nome], [cliente_dtnasc], [cliente_cpf], [cliente_telefone], [cliente_endereco], [cliente_email], [cliente_senha]) VALUES (2, N'Bruna Guimarães', CAST(0xCF1D0B00 AS Date), N'384.783.294-79', N'(94) 7394-7927', N'Rua baguidá', N'bruna@bruna.com.br', N'bruna123')
/****** Object:  Table [dbo].[Categoria]    Script Date: 05/25/2015 19:58:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Categoria](
	[categoria_id] [int] NOT NULL,
	[categoria_nome] [varchar](70) NULL,
PRIMARY KEY CLUSTERED 
(
	[categoria_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Categoria] ([categoria_id], [categoria_nome]) VALUES (1, N'Pães')
INSERT [dbo].[Categoria] ([categoria_id], [categoria_nome]) VALUES (2, N'Bebidas')
INSERT [dbo].[Categoria] ([categoria_id], [categoria_nome]) VALUES (3, N'Doces')
/****** Object:  Table [dbo].[Venda]    Script Date: 05/25/2015 19:58:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Venda](
	[venda_id] [int] NOT NULL,
	[cliente_id] [int] NULL,
	[valorTotal] [decimal](10, 2) NULL,
	[data_pedido] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[venda_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Produto]    Script Date: 05/25/2015 19:58:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Produto](
	[produto_id] [int] NOT NULL,
	[produto_nome] [varchar](70) NULL,
	[produto_preco] [decimal](10, 2) NULL,
	[produto_descricao] [varchar](max) NULL,
	[produto_imagem1] [varchar](50) NULL,
	[produto_imagem2] [varchar](50) NULL,
	[produto_imagem3] [varchar](50) NULL,
	[categoria_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[produto_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Produto] ([produto_id], [produto_nome], [produto_preco], [produto_descricao], [produto_imagem1], [produto_imagem2], [produto_imagem3], [categoria_id]) VALUES (1, N'Baguete de Frango', CAST(7.00 AS Decimal(10, 2)), N'Baguete recheada de frango, maionese, tomate, queijo e alface. 30cm', N'img_1_1.png', N'img_1_2.png', N'img_1_3.png', 1)
INSERT [dbo].[Produto] ([produto_id], [produto_nome], [produto_preco], [produto_descricao], [produto_imagem1], [produto_imagem2], [produto_imagem3], [categoria_id]) VALUES (2, N'Baguete de salame', CAST(9.00 AS Decimal(10, 2)), N'Baguete recheada de salame, maionese, tomate, queijo e alface. 30cm', N'img_2_1.png', N'img_2_2.png', N'img_2_3.png', 1)
INSERT [dbo].[Produto] ([produto_id], [produto_nome], [produto_preco], [produto_descricao], [produto_imagem1], [produto_imagem2], [produto_imagem3], [categoria_id]) VALUES (3, N'Brigadeiro', CAST(4.50 AS Decimal(10, 2)), N'Delicioso brigadeiro', N'img_3_1.png', N'img_3_2.jpg', N'img_3_3.png', 3)
INSERT [dbo].[Produto] ([produto_id], [produto_nome], [produto_preco], [produto_descricao], [produto_imagem1], [produto_imagem2], [produto_imagem3], [categoria_id]) VALUES (4, N'Café', CAST(4.00 AS Decimal(10, 2)), N'Café feito na hora', N'img_4_1.png', N'img_4_2.png', N'img_4_3.png', 2)
INSERT [dbo].[Produto] ([produto_id], [produto_nome], [produto_preco], [produto_descricao], [produto_imagem1], [produto_imagem2], [produto_imagem3], [categoria_id]) VALUES (5, N'Chá', CAST(3.50 AS Decimal(10, 2)), N'Chá de erva doce.', N'img_5_1.png', N'img_5_2.png', N'img_5_3.png', 2)
INSERT [dbo].[Produto] ([produto_id], [produto_nome], [produto_preco], [produto_descricao], [produto_imagem1], [produto_imagem2], [produto_imagem3], [categoria_id]) VALUES (6, N'Cookie Baunilinha', CAST(3.50 AS Decimal(10, 2)), N'Cookie de baunilha com M&M', N'img_6_1.png', N'img_6_2.png', N'img_6_3.png', 3)
INSERT [dbo].[Produto] ([produto_id], [produto_nome], [produto_preco], [produto_descricao], [produto_imagem1], [produto_imagem2], [produto_imagem3], [categoria_id]) VALUES (7, N'Cupcake de Negresco', CAST(4.00 AS Decimal(10, 2)), N'Cupcake sabor negresco', N'img_7_1.png', N'img_7_2.png', N'img_7_3.png', 3)
INSERT [dbo].[Produto] ([produto_id], [produto_nome], [produto_preco], [produto_descricao], [produto_imagem1], [produto_imagem2], [produto_imagem3], [categoria_id]) VALUES (8, N'Folhado de Palmito', CAST(7.00 AS Decimal(10, 2)), N'3 folhadinhos de palmito', N'img_8_1.png', N'img_8_2.png', N'img_8_3.png', 1)
INSERT [dbo].[Produto] ([produto_id], [produto_nome], [produto_preco], [produto_descricao], [produto_imagem1], [produto_imagem2], [produto_imagem3], [categoria_id]) VALUES (9, N'Pão de queijo com goiabada', CAST(4.00 AS Decimal(10, 2)), N'Nosso mais novo produto, um pão de queijo recheado com goiabada', N'img_9_1.png', N'img_9_2.png', N'img_9_3.png', 1)
INSERT [dbo].[Produto] ([produto_id], [produto_nome], [produto_preco], [produto_descricao], [produto_imagem1], [produto_imagem2], [produto_imagem3], [categoria_id]) VALUES (10, N'Sanduíche Peru e queijo', CAST(5.00 AS Decimal(10, 2)), N'Sanduíche natural de peru e queijo, mais alface e tomate', N'img_10_1.png', N'img_10_2.png', N'img_10_3.png', 1)
INSERT [dbo].[Produto] ([produto_id], [produto_nome], [produto_preco], [produto_descricao], [produto_imagem1], [produto_imagem2], [produto_imagem3], [categoria_id]) VALUES (11, N'Suco de laranja', CAST(4.50 AS Decimal(10, 2)), N'Suco natural de laranja com água', N'img_11_1.png', N'img_11_2.png', N'img_11_3.png', 2)
INSERT [dbo].[Produto] ([produto_id], [produto_nome], [produto_preco], [produto_descricao], [produto_imagem1], [produto_imagem2], [produto_imagem3], [categoria_id]) VALUES (12, N'Pão Francês', CAST(0.50 AS Decimal(10, 2)), N'Pão fresquinho da melhor qualidade.', N'img_12_1.png', N'img_12_2.png', N'img_12_3.png', 1)
/****** Object:  Table [dbo].[ItensVenda]    Script Date: 05/25/2015 19:58:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ItensVenda](
	[itensVenda_id] [int] NOT NULL,
	[venda_id] [int] NOT NULL,
	[produto_id] [int] NOT NULL,
	[quantidade] [int] NULL,
 CONSTRAINT [PK_ItensVenda] PRIMARY KEY CLUSTERED 
(
	[itensVenda_id] ASC,
	[venda_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  ForeignKey [FK_ItensVenda_Produto]    Script Date: 05/25/2015 19:58:47 ******/
ALTER TABLE [dbo].[ItensVenda]  WITH CHECK ADD  CONSTRAINT [FK_ItensVenda_Produto] FOREIGN KEY([produto_id])
REFERENCES [dbo].[Produto] ([produto_id])
GO
ALTER TABLE [dbo].[ItensVenda] CHECK CONSTRAINT [FK_ItensVenda_Produto]
GO
/****** Object:  ForeignKey [FK_ItensVenda_Venda]    Script Date: 05/25/2015 19:58:47 ******/
ALTER TABLE [dbo].[ItensVenda]  WITH CHECK ADD  CONSTRAINT [FK_ItensVenda_Venda] FOREIGN KEY([venda_id])
REFERENCES [dbo].[Venda] ([venda_id])
GO
ALTER TABLE [dbo].[ItensVenda] CHECK CONSTRAINT [FK_ItensVenda_Venda]
GO
/****** Object:  ForeignKey [FK_Produto_Categoria]    Script Date: 05/25/2015 19:58:47 ******/
ALTER TABLE [dbo].[Produto]  WITH CHECK ADD  CONSTRAINT [FK_Produto_Categoria] FOREIGN KEY([categoria_id])
REFERENCES [dbo].[Categoria] ([categoria_id])
GO
ALTER TABLE [dbo].[Produto] CHECK CONSTRAINT [FK_Produto_Categoria]
GO
/****** Object:  ForeignKey [FK_Venda_Cliente]    Script Date: 05/25/2015 19:58:47 ******/
ALTER TABLE [dbo].[Venda]  WITH CHECK ADD  CONSTRAINT [FK_Venda_Cliente] FOREIGN KEY([cliente_id])
REFERENCES [dbo].[Cliente] ([cliente_id])
GO
ALTER TABLE [dbo].[Venda] CHECK CONSTRAINT [FK_Venda_Cliente]
GO
