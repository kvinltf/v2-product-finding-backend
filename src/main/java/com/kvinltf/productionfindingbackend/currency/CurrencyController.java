package com.kvinltf.productionfindingbackend.currency;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * REST controller for managing currencies
 */
@RestController
@RequestMapping("/currencies")
@AllArgsConstructor
public class CurrencyController {
    private final CurrencyRepository currencyRepository;

    /**
     * Creates a new currency
     * 
     * @param request The request containing the currency details
     * @return The created currency
     */
    @PostMapping
    public Currency createCurrency(@Valid @RequestBody CurrencyCreateRequest request) {
        Currency currency = new Currency();
        currency.setName(request.name());
        currency.setCode(request.code());
        currency.setSymbol(request.symbol());
        currency.setDecimalPlaces(request.decimalPlaces());
        return currencyRepository.save(currency);
    }

    /**
     * Retrieves all currencies with pagination
     * 
     * @param pageable Pagination information
     * @return A page of currencies
     */
    @GetMapping
    public PagedModel<Currency> getAllCurrencies(
            @PageableDefault(size = 20, direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        Page<Currency> all = currencyRepository.findAll(pageable);
        return new PagedModel<>(all);
    }

    /**
     * Retrieves a currency by its ID
     * 
     * @param id The ID of the currency
     * @return The currency, if found
     */
    @GetMapping("/{id}")
    public Optional<Currency> getCurrencyById(@PathVariable Long id) {
        return currencyRepository.findById(id);
    }

    /**
     * Updates an existing currency
     * 
     * @param id The ID of the currency to update
     * @param request The request containing the updated currency details
     * @return The updated currency
     */
    @PutMapping("/{id}")
    public Currency updateCurrency(@PathVariable Long id, @Valid @RequestBody CurrencyUpdateRequest request) {
        Currency currency = currencyRepository.findById(id).orElseThrow();
        currency.setName(request.name());
        currency.setCode(request.code());
        currency.setSymbol(request.symbol());
        currency.setDecimalPlaces(request.decimalPlaces());
        return currencyRepository.save(currency);
    }

    /**
     * Deletes a currency
     * 
     * @param id The ID of the currency to delete
     */
    @DeleteMapping("/{id}")
    public void deleteCurrency(@PathVariable Long id) {
        currencyRepository.deleteById(id);
    }
}
