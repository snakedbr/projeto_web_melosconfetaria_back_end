package br.com.apiconfeitaria.projeto.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.apiconfeitaria.projeto.DAO.IProduto;
import br.com.apiconfeitaria.projeto.model.Produto;

@RestController
@CrossOrigin("*")
@RequestMapping("/produtos")
public class ProdutoController {

	private static String caminhoImagens = "D:/Faculdade/ProjetoWeb/front_end/imgs/";

	@Autowired
	private IProduto dao;

	// só para não usar um implements

	@GetMapping
	public List<Produto> listaprodutos() {
		return (List<Produto>) dao.findAll();

	}

	@PostMapping // estou criando esse método para criar objeto mas preciso especificar o local
					// do Post
	/* public Produto criarProduto(@RequestBody Produto produto, @RequestParam("file") MultipartFile arquivo) {
		Produto produtoNovo = dao.save(produto);
		return produtoNovo;

		// uso o postMan para testar o método
		
		try {
			if(!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoImagens+arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	} */
	
	public Produto criarProduto(@RequestParam("produto") String produtoJson, @RequestParam("file") MultipartFile arquivo) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    Produto produto = mapper.readValue(produtoJson, Produto.class);
	    
	    Produto produtoNovo = dao.save(produto);

	    if (!arquivo.isEmpty()) {
	        byte[] bytes = arquivo.getBytes();
	        String nomeArquivo = produtoNovo.getId_produto() + "_" + arquivo.getOriginalFilename();
	        Path caminho = Paths.get(caminhoImagens + nomeArquivo);
	        Files.write(caminho, bytes);
	        
	        produtoNovo.setImagem_produto(nomeArquivo);
	        produtoNovo = dao.save(produtoNovo);
	    }

	    return produtoNovo;
	}

	@PutMapping // método para editar
	public Produto editarProduto(@RequestBody Produto produto) {
		Produto produtoNovo = dao.save(produto);
		return produtoNovo;
	}

	@DeleteMapping("/{id_produto}") // método para deletar
	public Optional<Produto> excluirProduto(@PathVariable Integer id_produto) {
		Optional<Produto> produto = dao.findById(id_produto);
		dao.deleteById(id_produto);
		return produto;
	}

}
