public class TestClientDashboard {
    public static void main(String[] args) {
        try {
            System.out.println("Test de création ClientDashboard...");
            ui.client.ClientDashboard dashboard = new ui.client.ClientDashboard();
            System.out.println("ClientDashboard créé avec succès !");
            dashboard.setVisible(true);
        } catch (Exception e) {
            System.err.println("Erreur lors de la création de ClientDashboard:");
            e.printStackTrace();
        }
    }
}
