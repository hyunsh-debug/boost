package kr.or.connect.reservation.dao;

public class Sqls {
	public static final String SELECT_PROMOTION = 	
			"SELECT promotion.id, product.id AS productId, file_info.save_file_name AS saveFileImage, product.description, display_info.place_name AS placename " +
			" FROM promotion join product ON promotion.product_id = product.id" +
			" JOIN product_image ON product_image.product_id = product.id AND product_image.type = 'th'" +
			" JOIN file_info ON file_info.id = product_image.file_id" +
			" JOIN display_info ON promotion.product_id = display_info.product_id";
	
	public static final String CATEGORY_COUNT = 
			"SELECT category.id, category.name, COUNT(*) AS count FROM category" +
			" JOIN product on category.id = product.category_id GROUP BY category.id";
	
	public static final String SELECT_COUNT_PRODUCT_ALL = 
			"SELECT COUNT(*) FROM display_info";
	
	public static final String SELECT_COUNT_CATEGORY_BY_ID = 
			"SELECT COUNT(*) FROM product JOIN display_info ON product.id = display_info.product_id" +
			" JOIN category ON category.id = product.category_id where category.id = :id";
	
	public static final String SELECT_PRODUCT_ALL =
			"SELECT display_info.id as displayInfoId, product.id as productId, product.description, display_info.place_name, product.content, file_info.save_file_name, product_image.type" + 
			" FROM product JOIN display_info ON display_info.product_id = product.id" + 
			" JOIN product_image ON product.id = product_image.product_id" + 
			" JOIN file_info ON product_image.file_id = file_info.id where type = 'th'" +
			" ORDER BY display_info.id LIMIT :limit OFFSET :start ";		
	
	public static final String SELECT_PRODUCT_BY_CATEGORY_ID =
			"SELECT display_info.id as displayInfoId, product.id as productId, product.description, display_info.place_name, product.content, file_info.save_file_name, product_image.type" + 
			" FROM product JOIN display_info ON display_info.product_id = product.id" + 
			" JOIN product_image ON product.id = product_image.product_id" + 
			" JOIN file_info ON product_image.file_id = file_info.id WHERE type = 'th' AND category_id = :categoryId" +
			" ORDER BY display_info.id LIMIT 4 OFFSET :start ";		
	
	public static final String SELECT_DISPLAYINFO_BY_DISPLAYINFOID = 
			"SELECT display_info.product_id, category.id AS categoryId, display_info.id AS displayInfoId, category.name AS categoryName, product.description AS productDescription, product.content AS productContent, product.event AS productEvent, display_info.opening_hours, display_info.place_name, display_info.place_lot, display_info.place_street, display_info.tel AS telephone, display_info.homepage, display_info.email, display_info.create_date, display_info.modify_date" +
			" FROM display_info join product on product.id = display_info.product_id " +
			" join category on category.id = product.category_id " +
			" where display_info.id = :displayInfoId"; 
	
	public static final String SELECT_PRODUCTIMAGE_BY_DISPLAYINFOID =
			"SELECT product_image.product_id, product_image.id AS productImageId, product_image.type,  product_image.file_id AS fileInfoId, file_info.file_name, file_info.save_file_name, file_info.content_type, file_info.delete_flag, file_info.create_date, file_info.modify_date" +
			" FROM product_image" +
			" JOIN product ON product.id = product_image.product_id" +
			" JOIN file_info ON file_info.id = product_image.file_id" +
			" JOIN display_info ON display_info.id = product.id" +
			" WHERE product_image.type IN('ma', 'et') AND display_info.id = :displayInfoId";
	
	public static final String SELECT_DISPLAYINFOIMAGE_BY_DISPLAYINFOID = 
			"SELECT display_info_image.id AS displayInfoImageId, display_info_image.display_info_id AS displayInfoId, display_info_image.file_id, file_info.file_name, file_info.save_file_name, file_info.content_type, file_info.delete_flag,  file_info.create_date, file_info.modify_date" +
			" FROM display_info_image JOIN file_info ON file_info.id = display_info_image.file_id" + 
			" WHERE display_info_image.display_info_id = :displayInfoId";
	
	public static final String SELECT_PRODUCTPRICES_BY_DISPLAYINFOID = 
			"SELECT product_price.id AS productPriceId, product_price.product_id, product_price.price_type_name, product_price.price, product_price.discount_rate, product_price.create_date, product_price.modify_date" + 
			" FROM product_price" + 
			" JOIN product ON product.id = product_price.product_id" + 
			" JOIN display_info ON display_info.id = product_price.product_id" + 
			" WHERE display_info.id = :displayInfoId ORDER BY product_price.id DESC";
	
	public static final String SELECT_COMMENT_BY_DISPLAYINFOID = 
			"SELECT reservation_user_comment.comment, reservation_user_comment.id AS commentId, reservation_user_comment.create_date, reservation_user_comment.modify_date, reservation_user_comment.product_id, reservation_info.reservation_date, reservation_info.reservation_email, reservation_user_comment.reservation_info_id, reservation_info.reservation_name, reservation_info.reservation_tel AS reservationTelephone, reservation_user_comment.score" + 
			" FROM reservation_user_comment" +
			" JOIN reservation_info ON reservation_info.id = reservation_user_comment.reservation_info_id" + 
			" JOIN product ON product.id = reservation_user_comment.product_id" + 
			" JOIN display_info ON display_info.id = product.id" + 
			" WHERE display_info.id = :displayInfoId ORDER BY commentId DESC;";
	
	public static final String SELECT_COMMENTIMAGE_BY_COMMENTID = 
			"SELECT file_info.content_type, file_info.create_date, file_info.delete_flag, file_info.id AS fileId, file_info.file_name, reservation_user_comment_image.id AS imageId, file_info.modify_date, reservation_user_comment_image.reservation_info_id, reservation_user_comment_image.reservation_user_comment_id, file_info.save_file_name" + 
			" FROM reservation_user_comment_image" +
			" JOIN file_info ON file_info.id = reservation_user_comment_image.file_id" + 
			" JOIN reservation_user_comment ON  reservation_user_comment.id = reservation_user_comment_image.reservation_user_comment_id"+
			" JOIN reservation_info ON reservation_info.id = reservation_user_comment_image.reservation_info_id" + 
			" WHERE reservation_user_comment_id = :reservationUserCommentId";
	
	public static final String SELECT_COMMENT_AVG_BY_DISPLAYINFOID =
			"SELECT AVG(reservation_user_comment.score) AS averageScore" +
			" FROM reservation_user_comment" +
			" JOIN reservation_info ON reservation_info.id = reservation_user_comment.reservation_info_id" +
			" Join product on product.id = reservation_user_comment.product_id" + 
			" Join display_info on display_info.id = product.id" + 
			" WHERE display_info.id = :displayInfoId";
	public static final String SELECT_RESERVATION_INFO_BY_EMAIL =
			"SELECT * FROM reservation_info WHERE reservation_email = :resrvEmail";
	
	public static final String SELECT_TOTAL_PRICE_BY_RESERVATIONID = 
			"SELECT SUM((price-(price*(discount_rate/100)))*reservation_info_price.count) AS totalPrice " +
			"FROM reservation_info "+
			"JOIN reservation_info_price ON reservation_info_price.reservation_info_id = reservation_info.id " +
			"JOIN product_price ON reservation_info_price.product_price_id = product_price.id " + 
			"WHERE reservation_info.id = :reservationInfoId";
	public static final String UPDATE_BY_ID = 
			"UPDATE reservation_info SET cancel_flag = true WHERE id = :reservationInfoId";
	
	public static final String SELECT_FILE_INFO_BY_USER_COMMENT_IMAGEID = 
			"SELECT file_name, save_file_name, content_type, delete_flag, create_date, modify_date " + 
			"FROM file_info JOIN reservation_user_comment_image ON  reservation_user_comment_image.file_id = file_info.id " + 
			"WHERE reservation_user_comment_image.id = :reservationUserCommentImageId";
}
