# Resumo dos Problemas Evitados com a Implementação Atual

| Problema                              | Status Atual / Solução                                                                                         |
|-------------------------------------|---------------------------------------------------------------------------------------------------------------|
| **N+1 Queries**                     | ✅ **Evitado!** Com `@EntityGraph(attributePaths = "categories")` no método `findAll(Pageable)`, produtos e categorias são carregados juntos numa única query, evitando múltiplas consultas extras. |
| StackOverflowError na serialização JSON | ✅ Evitado usando `@JsonIgnore` nas coleções bidirecionais (`categories` em `Product` e `products` em `Category`), prevenindo recursão infinita na serialização. |
| Serialização instável de Page        | ✅ Evitado com a configuração `@EnableSpringDataWebSupport(pageSerializationMode = PageSerializationMode.VIA_DTO)`, garantindo um JSON estável para páginas (`Page<T>`). |
| LazyInitializationException          | ✅ Evitado ao usar DTOs construídos dentro da sessão/transação e com as coleções carregadas via `@EntityGraph`. |
| ConcurrentModificationException      | ✅ Evitado ao usar `Set` para coleções e manipular cuidadosamente as operações sobre elas.                      |
| StackOverflow em `hashCode()` e `equals()` | ⚠️ Pode ocorrer se os métodos forem gerados automaticamente com Lombok incluindo coleções. Recomenda-se implementar manualmente para ignorar associações bidirecionais e evitar recursão. |

---

## Notas adicionais

- O uso de `@EntityGraph` permite otimizar as consultas JPA sem precisar escrever JPQL explícito.
- O mapeamento para DTOs melhora o desempenho e evita exposição direta das entidades.
- A configuração de serialização do `Page` previne warnings e garante compatibilidade na API REST.


