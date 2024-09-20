package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

class CategoryTest {

    @Test
    @DisplayName("Dado que foram informados parâmetros válidos, quando invocar uma nova categoria, então deve instanciar uma categoria")
    public void testNewCategory(){
        final String expectedName = "Filmes";
        final String expectedDescription = "Categoria de filmes";
        final boolean expectedIsActive = true;

        final Category actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertTrue(actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    @DisplayName("Dado que um nome nulo foi informado, quando invocar uma nova categoria e a sua validação, então deve retornar um erro")
    public void testValidateCategoryNullName(){
        final String expectedName = null;
        final String expectedDescription = "Categoria de filmes";
        final String expectedErrorMessage = "O nome não pode ser nulo";
        final int expectedErrorsCount = 1;
        final boolean expectedIsActive = true;

        final DomainException exception = Assertions.assertThrows(DomainException.class, () -> Category.newCategory(expectedName, expectedDescription, expectedIsActive));

        Assertions.assertEquals(expectedErrorMessage, exception.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorsCount, exception.getErrors().size());

    }

    @Test
    @DisplayName("Dado que um nome vazio foi informado, quando invocar uma nova categoria e a sua validação, então deve retornar um erro")
    public void testValidateCategoryEmptyName(){
        final String expectedName = "";
        final String expectedDescription = "Categoria de filmes";
        final String expectedErrorMessage = "O nome não pode ser vazio";
        final int expectedErrorsCount = 1;
        final boolean expectedIsActive = true;

        //final Category actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final DomainException exception = Assertions.assertThrows(DomainException.class, () -> Category.newCategory(expectedName, expectedDescription, expectedIsActive));

        Assertions.assertEquals(expectedErrorMessage, exception.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorsCount, exception.getErrors().size());

    }

    @Test
    @DisplayName("Dado que um nome com menos de 3 caracteres foi informado, quando invocar uma nova categoria e a sua validação, então deve retornar um erro")
    public void testValidateCategoryNameLengthLessThan3(){
        final String expectedName = "Af ";
        final String expectedDescription = "Categoria de filmes";
        final String expectedErrorMessage = "O nome precisa ter no mínimo 3 caracteres";
        final int expectedErrorsCount = 1;
        final boolean expectedIsActive = true;

        final DomainException exception = Assertions.assertThrows(DomainException.class, () -> Category.newCategory(expectedName, expectedDescription, expectedIsActive));

        Assertions.assertEquals(expectedErrorMessage, exception.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorsCount, exception.getErrors().size());

    }

    @Test
    @DisplayName("Dado que um nome com mais de 255 caracteres foi informado, quando invocar uma nova categoria e a sua validação, então deve retornar um erro")
    public void testValidateCategoryNameLengthMoreThan255(){
        final String expectedName = """
                O Fabuloso Gerador de Lero-lero v2.0 é capaz de gerar qualquer quantidade de texto vazio e prolixo, ideal para engrossar uma tese de mestrado, impressionar seu chefe ou preparar discursos capazes de curar a insônia da platéia. Basta informar um título pomposo qualquer (nos moldes do que está sugerido aí embaixo) e a quantidade de frases desejada. Voilá! Em dois nano-segundos você terá um texto - ou mesmo um livro inteiro - pronto para impressão. Ou, se preferir, faça copy/paste para um editor de texto para formatá-lo mais sofisticadamente. Lembre-se: aparência é tudo, conteúdo é nada.
                
                O Gerador de Lero-lero para TI e informática foi baseado no Fabuloso Gerador de Lero-lero v2.0. Ele é capaz de gerar qualquer quantidade de texto vazio e prolixo, ideal para engrossar uma tese de mestrado, impressionar seu chefe ou preparar discursos capazes de curar a insônia da platéia. Basta informar um título pomposo qualquer (nos moldes do que está sugerido aí embaixo) e a quantidade de frases desejada. Voilá! Em dois nano-segundos você terá um texto - ou mesmo um livro inteiro - pronto para impressão. Ou, se preferir, faça copy/paste para um editor de texto para formatá-lo mais sofisticadamente. Lembre-se: aparência é tudo, conteúdo é nada.
                """;
        final String expectedDescription = "Categoria de filmes";
        final String expectedErrorMessage = "O nome precisa ter no máximo 255 caracteres";
        final int expectedErrorsCount = 1;
        final boolean expectedIsActive = true;

        final DomainException exception = Assertions.assertThrows(DomainException.class, () -> Category.newCategory(expectedName, expectedDescription, expectedIsActive));

        Assertions.assertEquals(expectedErrorMessage, exception.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorsCount, exception.getErrors().size());
    }

    @Test
    @DisplayName("Dado que uma descrição vazia foi informada, quando invocar uma nova categoria, então deve instanciar uma categoria")
    public void testNewCategoryEmptyDescription(){
        final String expectedName = "Filmes";
        final String expectedDescription = "";
        final boolean expectedIsActive = true;

        final Category actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(true, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    @DisplayName("Dado que uma descrição nula foi informada, quando invocar uma nova categoria, então deve instanciar uma categoria")
    public void testNewCategoryNullDescription(){
        final String expectedName = "Filmes";
        final String expectedDescription = null;
        final boolean expectedIsActive = true;

        final Category actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(true, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    @DisplayName("Dado uma categoria válida e ativa, quando desativar esta categoria, então a categoria deve ficar desativada")
    public void testDeactivateCategory() {
        final String expectedName = "Filmes";
        final String expectedDescription = "Filmes";
        final boolean expectedIsActive = true;

        final Category newCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertNotNull(newCategory);
        Assertions.assertNotNull(newCategory.getId());
        Assertions.assertEquals(expectedName, newCategory.getName());
        Assertions.assertEquals(expectedDescription, newCategory.getDescription());
        Assertions.assertTrue(newCategory.isActive());
        Assertions.assertNotNull(newCategory.getCreatedAt());
        Assertions.assertNotNull(newCategory.getUpdatedAt());
        Assertions.assertNull(newCategory.getDeletedAt());

        final Instant lastUpdate = newCategory.getUpdatedAt();

        final Category deactivatedCategory = newCategory.deactivate();

        Assertions.assertNotNull(deactivatedCategory);
        Assertions.assertEquals(newCategory.getId(), deactivatedCategory.getId());
        Assertions.assertEquals(newCategory.getName(), deactivatedCategory.getName());
        Assertions.assertEquals(newCategory.getDescription(), deactivatedCategory.getDescription());
        Assertions.assertFalse(deactivatedCategory.isActive());
        Assertions.assertEquals(newCategory.getCreatedAt(), deactivatedCategory.getCreatedAt());
        Assertions.assertNotEquals(lastUpdate, deactivatedCategory.getUpdatedAt());
        Assertions.assertNotNull(deactivatedCategory.getDeletedAt());
    }

    @Test
    @DisplayName("Dado uma categoria válida e inativa, quando ativar esta categoria, então a categoria deve ficar ativa")
    public void testActivateCategory() {
        final String expectedName = "Filmes";
        final String expectedDescription = "Filmes";
        final boolean expectedIsActive = false;

        final Category newCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertNotNull(newCategory);
        Assertions.assertNotNull(newCategory.getId());
        Assertions.assertEquals(expectedName, newCategory.getName());
        Assertions.assertEquals(expectedDescription, newCategory.getDescription());
        Assertions.assertFalse(newCategory.isActive());
        Assertions.assertNotNull(newCategory.getCreatedAt());
        Assertions.assertNotNull(newCategory.getUpdatedAt());
        Assertions.assertNotNull(newCategory.getDeletedAt());

        final Instant lastUpdate = newCategory.getUpdatedAt();

        final Category activatedCategory = newCategory.activate();

        Assertions.assertNotNull(activatedCategory);
        Assertions.assertEquals(newCategory.getId(), activatedCategory.getId());
        Assertions.assertEquals(newCategory.getName(), activatedCategory.getName());
        Assertions.assertEquals(newCategory.getDescription(), activatedCategory.getDescription());
        Assertions.assertTrue(activatedCategory.isActive());
        Assertions.assertEquals(newCategory.getCreatedAt(), activatedCategory.getCreatedAt());
        Assertions.assertNotEquals(lastUpdate, activatedCategory.getUpdatedAt());
        Assertions.assertNull(activatedCategory.getDeletedAt());
    }

    @Test
    @DisplayName("Dado uma categoria válida, quando alterar o nome da categoria, então a categoria deve ficar com o nome novo")
    public void testUpdateCategoryName() {
        final String name = "Filmes";
        final String description = "Filmes";
        final boolean isActive = true;

        final Category newCategory = Category.newCategory(name, description, isActive);

        Assertions.assertNotNull(newCategory);
        Assertions.assertNotNull(newCategory.getId());
        Assertions.assertEquals(name, newCategory.getName());
        Assertions.assertEquals(description, newCategory.getDescription());
        Assertions.assertTrue(newCategory.isActive());
        Assertions.assertNotNull(newCategory.getCreatedAt());
        Assertions.assertNotNull(newCategory.getUpdatedAt());
        Assertions.assertNull(newCategory.getDeletedAt());

        final Instant lastUpdate = newCategory.getUpdatedAt();

        final String newName = "Filmes alterado";
        final String newDescription = "Filmes";

        final Category updatedCategory = Assertions.assertDoesNotThrow(() -> newCategory.changeName(newName));

        Assertions.assertNotNull(updatedCategory);
        Assertions.assertEquals(newCategory.getId(), updatedCategory.getId());

        Assertions.assertEquals(updatedCategory.getName(), newName);
        Assertions.assertEquals(newCategory.getDescription(), updatedCategory.getDescription());
        Assertions.assertTrue(updatedCategory.isActive());
        Assertions.assertEquals(newCategory.getCreatedAt(), updatedCategory.getCreatedAt());
        Assertions.assertNotEquals(lastUpdate, updatedCategory.getUpdatedAt());
        Assertions.assertNull(updatedCategory.getDeletedAt());
    }

    @Test
    @DisplayName("Dado uma categoria válida, quando alterar o nome da categoria por um nome inválido, então deve lançar erro")
    public void testTryUpdateCategoryNameWithAnInvalidName() {
        final String name = "Filmes";
        final String description = "Filmes";
        final String expectedErrorMessage = "O nome precisa ter no mínimo 3 caracteres";
        final int expectedErrorsCount = 1;
        final boolean isActive = true;

        final Category newCategory = Category.newCategory(name, description, isActive);

        Assertions.assertNotNull(newCategory);
        Assertions.assertNotNull(newCategory.getId());
        Assertions.assertEquals(name, newCategory.getName());
        Assertions.assertEquals(description, newCategory.getDescription());
        Assertions.assertTrue(newCategory.isActive());
        Assertions.assertNotNull(newCategory.getCreatedAt());
        Assertions.assertNotNull(newCategory.getUpdatedAt());
        Assertions.assertNull(newCategory.getDeletedAt());

        final Instant lastUpdate = newCategory.getUpdatedAt();

        final String newName = "Fi";
        final String newDescription = "Filmes";

        final DomainException exception = Assertions.assertThrows(DomainException.class, () -> newCategory.changeName(newName));

        Assertions.assertEquals(expectedErrorMessage, exception.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorsCount, exception.getErrors().size());
    }

    @Test
    @DisplayName("Dado uma categoria válida, quando alterar o nome da categoria, então a categoria deve ficar com o nome novo")
    public void testUpdateCategoryDescription() {
        final String name = "Filmes";
        final String description = "Filmes";
        final boolean isActive = true;

        final Category newCategory = Category.newCategory(name, description, isActive);

        Assertions.assertNotNull(newCategory);
        Assertions.assertNotNull(newCategory.getId());
        Assertions.assertEquals(name, newCategory.getName());
        Assertions.assertEquals(description, newCategory.getDescription());
        Assertions.assertTrue(newCategory.isActive());
        Assertions.assertNotNull(newCategory.getCreatedAt());
        Assertions.assertNotNull(newCategory.getUpdatedAt());
        Assertions.assertNull(newCategory.getDeletedAt());

        final Instant lastUpdate = newCategory.getUpdatedAt();

        final String newDescription = "Filmes alterado";

        final Category updatedCategory = Assertions.assertDoesNotThrow(() -> newCategory.changeDescription(newDescription));

        Assertions.assertNotNull(updatedCategory);
        Assertions.assertEquals(newCategory.getId(), updatedCategory.getId());

        Assertions.assertEquals(name, updatedCategory.getName());
        Assertions.assertEquals(newDescription, updatedCategory.getDescription());
        Assertions.assertTrue(updatedCategory.isActive());
        Assertions.assertEquals(newCategory.getCreatedAt(), updatedCategory.getCreatedAt());
        Assertions.assertNotEquals(lastUpdate, updatedCategory.getUpdatedAt());
        Assertions.assertNull(updatedCategory.getDeletedAt());
    }

}