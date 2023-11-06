package br.com.joaoqnt.lista2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.joaoqnt.lista2.model.Produto;
import br.com.joaoqnt.lista2.repository.ProdutoRepository;
import org.springframework.ui.Model;

@Controller
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/list")
    public String listProdutos(Model model) {
        model.addAttribute("produto", produtoRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "add";
    }

    @PostMapping("/add")
    public String addProduto(@ModelAttribute Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        model.addAttribute("produto", produto);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editProduto(@ModelAttribute Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/list";
    }
}
