USE RecipeManager

if (select count(*) from dbo.RecipeType) = 0
begin
	insert into [dbo].[RecipeType](description)
    values('Vegetarian'),('NonVegetarian');
end
