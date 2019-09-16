package com.locadoraApi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.locadoraApi.modelo.Marca;
import com.locadoraApi.modelo.Modelo;

public class ModeloDao {

	public static boolean inserir(Modelo modelo) {
		
		try {
			
			String sql = "insert into \"Modelo\" (id_marca, descricao) values (?,?)";

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);

			pstm.setInt(1, modelo.getMarca().getId());
			pstm.setString(2, modelo.getDescricao());
			pstm.executeUpdate();
			return true;
			
		} catch (Exception e) {
			
			System.out.print("Erro ao inserir modelo! " + e.getMessage());
			return false;
			
		}
	}

	public static boolean alterar(Modelo modelo) {
		
		try {
			
			String sql = "update  \"Modelo\" set descricao = ?, id_marca= ? where id_modelo= ?";

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, modelo.getDescricao());
			pstm.setInt(2, modelo.getMarca().getId());
			pstm.setInt(3, modelo.getId());
			pstm.executeUpdate();
			return true;
			
		} catch (Exception e) {
			
			System.out.print("Erro ao alterar o modelo! " + e.getMessage());
			return false;
			
		}
		
	}

	public static boolean excluir(Modelo modelo) {
		try {
			String sql = "delete from Modelo where id_modelo= ?";

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, modelo.getId());
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.print("Erro ao excluir o modelo! " + e.getMessage());
			return false;
		}
	}

	public static List<Modelo> listagem() {
		
		try {
			String sql = "select * from \"Modelo\" order by descricao";
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();

			List<Modelo> listaModelos = new ArrayList<Modelo>();
			
			while (rs.next()) {
				Modelo m = new Modelo();
				m.setId(rs.getInt("id_modelo"));
				m.setDescricao(rs.getString("descricao"));

				Marca ma = new Marca();

				m.setMarca(MarcaDao.retornaPorId(rs.getInt("id_marca")));

				listaModelos.add(m);

			}
			return listaModelos;
			
		} catch (Exception e) {
			System.out.print("Erro ao listar os modelos! " + e.getMessage());
			return null;
		}
		
	}

	public static Modelo retornaPorId(int id) {
		
		try {

			String sql = "SELECT * from \"Modelo\" where id_modelo= ?";
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();

			rs.next();
			Modelo m = new Modelo();

			m.setId(rs.getInt("id_modelo"));
			m.setDescricao(rs.getString("descricao"));
			Marca ma = new Marca();

			m.setMarca(MarcaDao.retornaPorId(rs.getInt("id_marca")));
			return m;

		} catch (Exception e) {
			
			System.out.print("Erro ao listar o modelo! " + e.getMessage());
			return null;
			
		}

	}

	public static int retornaUltimoId() {
		
		try {

			String sql = "SELECT MAX(id_modelo) as ultimoID from \"Modelo\"";
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			rs.next();
			return rs.getInt("ultimoID");
			
		} catch (Exception e) {
			return 0;
		}

	}
	

}
