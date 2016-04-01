package version_1_1;

import java.util.Vector;

public class Zakaz {
	
	int id_zakaz;// порядковый номер заказа
	Summ suma_zakaza;// стоимость заказа
	boolean payed_zakaz; // оплачен- неоплачен
	Vector<Menu> vectorRegistratedZakaz = new Vector(); //список заказанных объектов Меню
	String timeReception; //время принятия заказа
	String timeDelivery; //время выдачи заказа
	Staff waiter; //сотрудник(официант) который обслуживает заказ
	
	public enum OrderStatus { ACCEPTED, IN_PROCESSING, ISSUED, CANCELED }
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
