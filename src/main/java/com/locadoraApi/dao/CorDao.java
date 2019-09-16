package com.locadoraApi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.locadoraApi.modelo.Cor;

public class CorDao {
	
	public static boolean inserir(Cor cor) {
		
		try {
			
			String sql = "insert into cor (nome) values (?)";

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, cor.getNome());
			pstm.executeUpdate();
			return true;
			
		} catch (Exception e) {
			
			System.out.print("Erro ao inserir! " + e.getMessage());
			
			return false;
		}
		
	}

	public static boolean alterar(Cor cor) {
		
		try {
			
			String sql = "update cor set nome = ? where id_cor = ?";

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, cor.getNome());
			pstm.setInt(2, cor.getId());
			pstm.executeUpdate();
			return true;
			
		} catch (Exception e) {
			
			System.out.print("Erro ao alterar! " + e.getMessage());
			
			return false;
		}
		
	}

	public static boolean excluir(int id) {
		
		try {
			
			String sql = "delete from Cor where id_cor = ?";

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
	
	public static List<Cor> listagem() {
		
		try {
			
			String sql = "select * from cor order by nome";

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			List<Cor> listaCores = new ArrayList<Cor>();
			
			while (rs.next()) {
				Cor m = new Cor();
				m.setId(rs.getInt("id_cor"));
				m.setNome(rs.getString("nome"));
				listaCores.add(m);
			}
			
			return listaCores;
			
		} catch (Exception e) {
			
			System.out.print("Erro ao listar! " + e.getMessage());
			
			return null;
		}
	}
	
	public static Cor retornaPorId(int id) {
		
		try {
			
			String sql = "select * from cor where id_cor = ?";

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			
			rs.next();
			Cor m = new Cor();
			m.setId(rs.getInt("id_cor"));
			m.setNome(rs.getString("nome"));
			return m;
			
		} catch (Exception e) {
			
			System.out.print("Erro ao listar! " + e.getMessage());
			
			return null;
		}
		
	}

	public static int retornaUltimoId() {
		
		try {
			
			String sql = "select max(id_cor) as ultimo from cor";

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
