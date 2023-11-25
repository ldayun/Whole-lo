package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Post;

public class MarketDAO {
    private JDBCUtil jdbcUtil = null;

    public MarketDAO() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
    }

    // 냉장고
    // 글 중에서 타입이 마켓인 사용자의 글을 보여주기
    public List<Post> findtypeMarketPost() throws SQLException {

        String sql = "SELECT post_id, title, type, content, image,reg_date, writer_id "
                + "FROM POST "
                + "WHERE type = 'MARKET'";

        jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil에 query문 설정

//        jdbcUtil.setSqlAndParameters(query.toString(), null);        // JDBCUtil에 query문 설정
        try {
            ResultSet rs = jdbcUtil.executeQuery(); // query 실행
           
            List<Post> postList = new ArrayList<Post>(); // User들의 리스트 생성
            while (rs.next()) {
                Post post = new Post();
                post.setPost_id(rs.getLong("post_id"));// Community 객체를 생성하여 현재 행의 정보를 저장
                post.setTitle(rs.getString("title"));
                post.setType(rs.getString("type"));
                post.setContent(rs.getString("content"));
                post.setImage(rs.getString("image"));
                post.setReg_date(rs.getDate("reg_date").toLocalDate());
                post.setWriter_id(rs.getLong("writer_id"));
                postList.add(post);             // List에 Community 객체 저장
            }
            return postList;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close(); // resource 반환
        }
        return null;
    }
    // 글 클릭하면 상세 글 보이도록
    // 사진, 제목, 가격, 지역, 태그, 업로드 날짜 가져오기

}
