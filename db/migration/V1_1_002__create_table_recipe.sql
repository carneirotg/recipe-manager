USE [RecipeManager]
GO

CREATE TABLE [dbo].[recipe](
    [id] [bigint] IDENTITY(1,1) NOT NULL,
    [created_at] [datetime2](7) NOT NULL,
    [is_vegetarian] bit NOT NULL,
    [suitable_for] [int] NOT NULL,
    [ingredients] [text] NOT NULL,
    [instructions] [text] NOT NULL,
 CONSTRAINT [PK_Recipe] PRIMARY KEY CLUSTERED
(
    [id] ASC
))
GO



