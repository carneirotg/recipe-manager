USE [RecipeManager]
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[recipe]') AND type in (N'U'))
DROP TABLE [dbo].[recipe]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[recipe](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](7) NOT NULL,
	[recipe_type] [varchar](50) NOT NULL,
	[suitable_for] [int] NOT NULL,
	[ingredients] [text] NOT NULL,
	[instructions] [text] NOT NULL,
 CONSTRAINT [PK_Recipe] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO


