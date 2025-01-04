package com.kvinltf.productionfindingbackend.currency;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/currencies")
@AllArgsConstructor
public class CurrencyController {
    private final CurrencyRepository currencyRepository;

    @PostMapping
    public Currency createCurrency(@RequestBody CurrencyCreateRequest request) {
        Currency currency = new Currency();
        currency.setName(request.name());
        currency.setDecimal(request.decimal());
        return currencyRepository.save(currency);
    }

    @GetMapping
    public PagedModel<Currency> getAllCurrencies(
            @PageableDefault(size = 20, direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        Page<Currency> all = currencyRepository.findAll(pageable);
        return new PagedModel<>(all);
    }

    @GetMapping("/{id}")
    public Optional<Currency> getCurrencyById(@PathVariable Long id) {
        return currencyRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Currency updateCurrency(@PathVariable Long id, @RequestBody CurrencyUpdateRequest request) {
        Currency currency = currencyRepository.findById(id).orElseThrow();
        currency.setName(request.name());
        currency.setDecimal(request.decimal());
        return currencyRepository.save(currency);
    }

    @DeleteMapping("/{id}")
    public void deleteCurrency(@PathVariable Long id) {
        currencyRepository.deleteById(id);
    }
}
