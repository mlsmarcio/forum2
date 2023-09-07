package br.com.alura.forum.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.alura.forum.modelo.Curso;

//Como é um projeto do spring boot
@RunWith(SpringRunner.class)
// mais apropriada para testar repository
@DataJpaTest
//a configuração do banco de dados não deve ser substituida pela do banco de dados em memória.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) 
//Quando rodar a classe de teste forçar a ativação do profile test
@ActiveProfiles("test") 
public class CursoRepositoryTest {

	@Autowired
	private CursoRepository repository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	public void deveriaCarregarUmCursoAoBuscarPeloSeuNome() {
		String nomeCurso = "HTML 5";
		
		Curso html5 = new Curso();
		html5.setNome(nomeCurso);
		html5.setCategoria("Programação");
		em.persist(html5);
		
		Curso curso = repository.findByNome(nomeCurso);
		Assert.assertNotNull(curso);
		Assert.assertEquals(nomeCurso, curso.getNome());
	}

	@Test
	public void nãoDeveriaCarregarUmCursoNaoExistente() {
		String nomeCurso = "HTML";
		Curso curso = repository.findByNome(nomeCurso);
		Assert.assertNull(curso);
	}

}
