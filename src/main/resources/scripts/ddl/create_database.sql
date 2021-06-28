USE [master]
GO

IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = 'RecipeManager')
    CREATE DATABASE [RecipeManager]
     CONTAINMENT = NONE
     ON  PRIMARY
    ( NAME = N'RecipeManager', FILENAME = N'/var/opt/mssql/data/RecipeManager.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
     LOG ON
    ( NAME = N'RecipeManager_log', FILENAME = N'/var/opt/mssql/data/RecipeManager_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
     WITH CATALOG_COLLATION = DATABASE_DEFAULT
END

