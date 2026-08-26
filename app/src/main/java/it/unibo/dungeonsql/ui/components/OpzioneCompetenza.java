package it.unibo.dungeonsql.ui.components;

public record OpzioneCompetenza(String codice, String descrizione) {
        @Override
        public String toString() {
            return descrizione;
        }
    }