package com.locadoraApi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import com.locadoraApi.modelo.Marca;

public class MarcaDao {
	
	public static boolean inserir(Marca marca) {
		
		try {
			
			String sql = "insert into marca (nome_marca) values (?)";

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, marca.getNomeMarca());
			pstm.executeUpdate();
			return true;
			
		} catch (Exception e) {
			
			System.out.print("Erro ao inserir! " + e.getMessage());
			
			return false;
		}
		
	}

	public static boolean alterar(Marca marca) {
		
		try {
			
			String sql = "update marca set nome_marca = ? where id_marca = ?";

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, marca.getNomeMarca());
			pstm.setInt(2, marca.getId());
			pstm.executeUpdate();
			return true;
			
		} catch (Exception e) {
			
			System.out.print("Erro ao alterar! " + e.getMessage());
			
			return false;
		}
		
	}

	public static boolean excluir(int id) {
		
		try {
			
			String sql = "delete from Marca where id_marca = ?";

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.executeUpdate();
			return true;
			
		} catch (Exception e) {
			
			System.out.print("Erro ao excluir! " + e.getMessage());
			
			return false;
		}
		
	}
	
	public static List<Marca> listagem() {
		
		try {
			
			String sql = "select * from marca order by nome_marca";

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			List<Marca> listaMarcas = new ArrayList<Marca>();
			
			while (rs.next()) {
				Marca m = new Marca();
				m.setId(rs.getInt("id_marca"));
				m.setNomeMarca(rs.getString("nome_marca"));
				listaMarcas.add(m);
			}
			
			return listaMarcas;
			
		} catch (Exception e) {
			
			System.out.print("Erro ao listar! " + e.getMessage());
			
			return null;
		}
	}
	
	public static Marca retornaPorId(int id) {
		
		try {
			
			String sql = "select * from marca where id_marca = ?";

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			
			rs.next();
			Marca m = new Marca();
			m.setId(rs.getInt("id_marca"));
			m.setNomeMarca(rs.getString("nome_marca"));
			return m;
			
		} catch (Exception e) {
			
			System.out.print("Erro ao listar! " + e.getMessage());
			
			return null;
		}
		
	}

	public static int retornaUltimoId() {
		
		try {
			
			String sql = "select max(id_marca) as ultimo from marca";

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			rs.next();
			
			return rs.getInt("ultimo");
			
		} catch (Exception e) {
			
			return 0;
			
		}
	}

}
