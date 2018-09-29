package br.com.exemplo.vendas.negocio.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.exemplo.vendas.negocio.entity.Item;
import br.com.exemplo.vendas.util.log.LoggerGenerator;

public class ItemDAO extends GenericDAO<Item> {

//	private static final Logger LOGGER = Logger.getLogger(ItemDAO.class);
	
	public ItemDAO(EntityManager em) {
		super(em);
	}

	public boolean inserir(Item item) {
		boolean result = Boolean.FALSE;

		try {
			em.persist(item);
			result = Boolean.TRUE;
			if (debugInfo) {
				LoggerGenerator.write("Item inserido: ", item.getResponsavel());
			}
		} catch (Exception e) {
			if (debugInfo) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean alterar(Item item) {
		boolean result = false;
		Item existenteItem = null;

		try {
			existenteItem = em.find(Item.class, item.getId());
			if (existenteItem != null) {
				em.merge(item);
				result = true;
				if (debugInfo) {
					LoggerGenerator.write("Item alterado: ", item.getResponsavel());
				}
			} else {
				result = false;
			}
		} catch (Exception e) {
			if (debugInfo) {
				e.printStackTrace();
			}
			result = false;
		}
		return result;
	}

	public boolean excluir(Item item) {
		Item obj = null;
		boolean result = false;

		try {
			Query q = em.createQuery("from Item where id = :id");
			q.setParameter("id", item.getId());
			obj = (Item) q.getSingleResult();
			em.remove(obj);
			result = Boolean.TRUE;
			if (debugInfo) {
				LoggerGenerator.write("Item excluido: ", item.getResponsavel());
			}
		} catch (Exception e) {
			if (debugInfo) {
				e.printStackTrace();
			}
		}
		return result;
	}

}