USE [master]
GO

IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = 'RecipeManager')
BEGIN
    CREATE DATABASE [RecipeManager]
END

