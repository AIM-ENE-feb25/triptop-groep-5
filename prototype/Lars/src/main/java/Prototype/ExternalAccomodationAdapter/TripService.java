package Prototype.ExternalAccomodationAdapter;

class TripService {
    private ExternalAccommodationAdapter adapter;

    public TripService(ExternalAccommodationAdapter adapter) {
        this.adapter = adapter;
    }

    public void requestAvailability() {
        adapter.request();
    }
}