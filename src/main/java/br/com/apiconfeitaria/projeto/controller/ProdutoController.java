package br.com.apiconfeitaria.projeto.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;

import br.com.apiconfeitaria.projeto.model.Produto;
import br.com.apiconfeitaria.projeto.repository.IProduto;
import br.com.apiconfeitaria.projeto.service.ProdutoService;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin("*")
@RequestMapping("/produtos")
public class ProdutoController {

	private static String caminhoImagens = "D:/Faculdade/ProjetoWeb/front_end/imgs/";

	@Autowired
	private IProduto dao;
	private ProdutoService produtoService;
	private final String bucketName = "meloconfeitaria-c38eb.appspot.com";  // Substitua pelo seu bucket
	
	public ProdutoController (ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	// só para não usar um implements

	@GetMapping
	public ResponseEntity<List<Produto>> listaprodutos() {
		return ResponseEntity.status(200).body(produtoService.listarProduto());

	}

	@PostMapping
	public ResponseEntity<Produto> criarProduto(@RequestParam("produto") String produtoJson, @RequestParam("file") MultipartFile arquivo) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    Produto produto = mapper.readValue(produtoJson, Produto.class);
	    
	    // Salvar o produto no banco de dados
	    Produto produtoNovo = dao.save(produto);

	    // Verificar se o arquivo não está vazio
	    if (!arquivo.isEmpty()) {
	        // Obter os bytes do arquivo
	        byte[] bytes = arquivo.getBytes();
	        String nomeArquivo = produtoNovo.getId_produto() + "_" + arquivo.getOriginalFilename();

	        // Carregar as credenciais da conta de serviço
	        FileInputStream serviceAccount = new FileInputStream("src/main/java/br/com/apiconfeitaria/projeto/firebase/key.json");
	        Storage storage = StorageOptions.newBuilder()
	            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
	            .build()
	            .getService();

	        BlobId blobId = BlobId.of(bucketName, nomeArquivo);
	        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(arquivo.getContentType()).build();
	        storage.create(blobInfo, bytes);

	        // Gerar a URL pública permanente
	        String publicUrl = String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", bucketName, nomeArquivo);

	        // Atualizar o produto com a URL pública da imagem
	        produtoNovo.setImagem_produto(publicUrl);
	        produtoNovo = dao.save(produtoNovo);  // Salva novamente com a URL da imagem
	    }

	    // Retornar o produto criado
	    return ResponseEntity.status(201).body(produtoService.criarProduto(produtoNovo));
	}
	 
	@PutMapping("/{id_produto}")
	public ResponseEntity<Produto> editarProduto(@PathVariable Integer id_produto, @RequestBody Produto produtoAtualizado) {
	    return dao.findById(id_produto)
	        .map(produto -> {
	            if (produtoAtualizado.getNome_produto() != null) {
	                produto.setNome_produto(produtoAtualizado.getNome_produto());
	            }
	            if (produtoAtualizado.getPreco_venda() != null) {
	                produto.setPreco_venda(produtoAtualizado.getPreco_venda());
	            }
	            if (produtoAtualizado.getId_categoria() != null) {
	                produto.setId_categoria(produtoAtualizado.getId_categoria());
	            }
	            if (produtoAtualizado.getDescricao_produto() != null) {
	                produto.setDescricao_produto(produtoAtualizado.getDescricao_produto());
	            }
	            // Atualize outros campos conforme necessário
	            Produto produtoSalvo = dao.save(produto);
	            return ResponseEntity.ok(produtoSalvo);
	        })
	        .orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id_produto}") // método para deletar
	public ResponseEntity<?> excluirProduto(@PathVariable Integer id_produto) {
		produtoService.excluirProduto(id_produto);
		return ResponseEntity.status(204).build();
	}

}
