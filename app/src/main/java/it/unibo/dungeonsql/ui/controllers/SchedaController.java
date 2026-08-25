package it.unibo.dungeonsql.ui.controllers;

import it.unibo.dungeonsql.dtos.SchedaPersonaggio;
import it.unibo.dungeonsql.models.*;
import it.unibo.dungeonsql.services.SchedaService.RigaInventario;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.*;

public class SchedaController extends ScrollPane {

    @FXML private Label characterNameLabel;
    @FXML private Label backgroundLabel;
    @FXML private Label creatorLabel;
    @FXML private Label raceLabel;
    @FXML private Label alignmentLabel;
    @FXML private Label experienceLabel;

    @FXML private Label strengthScoreLabel;
    @FXML private Label strengthModifierLabel;
    @FXML private Label strengthSavingLabel;

    @FXML private Label dexterityScoreLabel;
    @FXML private Label dexterityModifierLabel;
    @FXML private Label dexteritySavingLabel;

    @FXML private Label constitutionScoreLabel;
    @FXML private Label constitutionModifierLabel;
    @FXML private Label constitutionSavingLabel;

    @FXML private Label intelligenceScoreLabel;
    @FXML private Label intelligenceModifierLabel;
    @FXML private Label intelligenceSavingLabel;

    @FXML private Label wisdomScoreLabel;
    @FXML private Label wisdomModifierLabel;
    @FXML private Label wisdomSavingLabel;

    @FXML private Label charismaScoreLabel;
    @FXML private Label charismaModifierLabel;
    @FXML private Label charismaSavingLabel;

    @FXML private Label proficiencyBonusLabel;
    @FXML private Label combatProficiencyBonusLabel;

    @FXML private Label acrobaticsProfMark;
    @FXML private Label acrobaticsBonusLabel;
    @FXML private Label animalHandlingProfMark;
    @FXML private Label animalHandlingBonusLabel;
    @FXML private Label arcanaProfMark;
    @FXML private Label arcanaBonusLabel;
    @FXML private Label athleticsProfMark;
    @FXML private Label athleticsBonusLabel;
    @FXML private Label deceptionProfMark;
    @FXML private Label deceptionBonusLabel;
    @FXML private Label historyProfMark;
    @FXML private Label historyBonusLabel;
    @FXML private Label insightProfMark;
    @FXML private Label insightBonusLabel;
    @FXML private Label intimidationProfMark;
    @FXML private Label intimidationBonusLabel;
    @FXML private Label investigationProfMark;
    @FXML private Label investigationBonusLabel;
    @FXML private Label medicineProfMark;
    @FXML private Label medicineBonusLabel;
    @FXML private Label natureProfMark;
    @FXML private Label natureBonusLabel;
    @FXML private Label perceptionProfMark;
    @FXML private Label perceptionBonusLabel;
    @FXML private Label performanceProfMark;
    @FXML private Label performanceBonusLabel;
    @FXML private Label persuasionProfMark;
    @FXML private Label persuasionBonusLabel;
    @FXML private Label religionProfMark;
    @FXML private Label religionBonusLabel;
    @FXML private Label sleightOfHandProfMark;
    @FXML private Label sleightOfHandBonusLabel;
    @FXML private Label stealthProfMark;
    @FXML private Label stealthBonusLabel;
    @FXML private Label survivalProfMark;
    @FXML private Label survivalBonusLabel;
    @FXML private Label passivePerceptionLabel;

    @FXML private Label armorClassLabel;
    @FXML private Label initiativeLabel;
    @FXML private Label speedLabel;
    @FXML private Label currentHpLabel;
    @FXML private Label maxHpLabel;
    @FXML private Label hitDiceLabel;
    @FXML private Label sizeLabel;
    @FXML private Label spellAttackBonusLabel;
    @FXML private Label spellSaveDcLabel;

    @FXML private TableView<AttackRow> attacksTable;
    @FXML private TableColumn<AttackRow, String> attackNameColumn;
    @FXML private TableColumn<AttackRow, String> attackBonusColumn;
    @FXML private TableColumn<AttackRow, String> attackDamageColumn;

    @FXML private TableView<SpellRow> spellsTable;
    @FXML private TableColumn<SpellRow, String> spellNameColumn;
    @FXML private TableColumn<SpellRow, String> spellLevelColumn;
    @FXML private TableColumn<SpellRow, String> spellRitualColumn;
    @FXML private TableColumn<SpellRow, String> spellDescriptionColumn;

    @FXML private TableView<ClassRow> classesTable;
    @FXML private TableColumn<ClassRow, String> classNameColumn;
    @FXML private TableColumn<ClassRow, String> subclassNameColumn;
    @FXML private TableColumn<ClassRow, String> classLevelColumn;

    @FXML private ListView<String> classTraitsList;

    @FXML private TableView<ResourceRow> classResourcesTable;
    @FXML private TableColumn<ResourceRow, String> resourceNameColumn;
    @FXML private TableColumn<ResourceRow, String> resourceRecoveryColumn;

    @FXML private TableView<ItemRow> inventoryTable;
    @FXML private TableColumn<ItemRow, String> itemNameColumn;
    @FXML private TableColumn<ItemRow, String> itemQuantityColumn;
    @FXML private TableColumn<ItemRow, String> itemWeightColumn;
    @FXML private TableColumn<ItemRow, String> itemDescriptionColumn;

    @FXML private Label backgroundDescriptionLabel;
    @FXML private Label raceDescriptionLabel;
    @FXML private Label baseSpeedLabel;
    @FXML private Label darkvisionLabel;

    @FXML private Button backBtn;

    private final Runnable goBack;

    private final SchedaPersonaggio scheda;

    public SchedaController(SchedaPersonaggio scheda, Runnable goBack) {
        this.scheda = scheda;
        this.goBack = goBack;

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/it/unibo/dungeonsql/ui/views/scheda_view.fxml")
        );
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento di scheda_view.fxml", e);
        }

        if (scheda != null && scheda.getPersonaggio() != null) {
            popolaVista();
        }
    }

    @FXML
    private void backBtnHandler() {
        this.goBack.run();
    }

    @FXML
    private void initialize() {
        configuraTabelle();
    }

    private void configuraTabelle() {
        attacksTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        spellsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        classesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        classResourcesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        inventoryTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        attackNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().name()));
        attackBonusColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().bonus()));
        attackDamageColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().damage()));

        spellNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().name()));
        spellLevelColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().level()));
        spellRitualColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().ritual()));
        spellDescriptionColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().description()));

        classNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().className()));
        subclassNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().subclassName()));
        classLevelColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().level()));

        resourceNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().name()));
        resourceRecoveryColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().recovery()));

        itemNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().name()));
        itemQuantityColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().quantity()));
        itemWeightColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().weight()));
        itemDescriptionColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().description()));
    }

    private void popolaVista() {
        Personaggio p = scheda.getPersonaggio();

        popolaHeader(p);
        popolaCaratteristiche();
        popolaCombattimento(p);
        popolaSkills();
        popolaMagie();
        popolaProgressi();
        popolaInventario();
        popolaInformazioniRazzaEBackground(p);
    }

    private void popolaHeader(Personaggio p) {
        Scheda s = p.getScheda();
        Background background = p.getBackground();
        Razza razza = p.getRazza();

        characterNameLabel.setText(safe(s != null ? s.getNome() : null));
        alignmentLabel.setText(safe(p.getAllineamento()));
        experienceLabel.setText(String.valueOf(p.getExpAccumulata()));
        creatorLabel.setText(safe(s != null && s.getCreatore() != null ? s.getCreatore().getUsername() : null));
        backgroundLabel.setText(safe(background != null ? background.getNomeBackground() : null));
        raceLabel.setText(safe(razza != null ? razza.getNomeRazza() : null));
    }

    private void popolaCaratteristiche() {
        Map<String, Possesso> byName = caratteristicheByName();
        int proficiency = calcolaBonusCompetenza();

        aggiornaCaratteristica(strengthScoreLabel, strengthModifierLabel, strengthSavingLabel, byName, proficiency, "forza");
        aggiornaCaratteristica(dexterityScoreLabel, dexterityModifierLabel, dexteritySavingLabel, byName, proficiency, "destrezza");
        aggiornaCaratteristica(constitutionScoreLabel, constitutionModifierLabel, constitutionSavingLabel, byName, proficiency, "costituzione");
        aggiornaCaratteristica(intelligenceScoreLabel, intelligenceModifierLabel, intelligenceSavingLabel, byName, proficiency, "intelligenza");
        aggiornaCaratteristica(wisdomScoreLabel, wisdomModifierLabel, wisdomSavingLabel, byName, proficiency, "saggezza");
        aggiornaCaratteristica(charismaScoreLabel, charismaModifierLabel, charismaSavingLabel, byName, proficiency, "carisma");

        proficiencyBonusLabel.setText(formatBonus(proficiency));
        combatProficiencyBonusLabel.setText(formatBonus(proficiency));
    }

    private void aggiornaCaratteristica(Label scoreLabel, Label modifierLabel, Label savingLabel,
                                         Map<String, Possesso> byName, int proficiency, String caratteristica) {
        Possesso possesso = byName.get(normalize(caratteristica));
        int score = possesso != null ? possesso.getPunteggio() : 10;
        boolean competente = possesso != null && Boolean.TRUE.equals(possesso.getCompetenzaSalvezza());

        int modifier = calcolaModificatore(score);
        int saving = modifier + (competente ? proficiency : 0);

        scoreLabel.setText(String.valueOf(score));
        modifierLabel.setText(formatBonus(modifier));
        savingLabel.setText(formatBonus(saving));
    }

    private void popolaCombattimento(Personaggio p) {
        Scheda s = p.getScheda();
        Razza razza = p.getRazza();

        int proficiency = calcolaBonusCompetenza();
        int dexModifier = calcolaModificatore(scoreOf("destrezza"));

        armorClassLabel.setText(String.valueOf(s != null ? s.getCa() : 10));
        initiativeLabel.setText(formatBonus(dexModifier));
        currentHpLabel.setText(String.valueOf(p.getHp()));
        maxHpLabel.setText("/ " + (s != null ? s.getMaxHp() : 0));
        proficiencyBonusLabel.setText(formatBonus(proficiency));
        combatProficiencyBonusLabel.setText(formatBonus(proficiency));
        sizeLabel.setText(s != null ? safe(s.getTaglia()) : "—");

        int speed = razza != null ? razza.getVelocitaBase() : 0;
        speedLabel.setText(speed > 0 ? speed + " m" : "—");
        baseSpeedLabel.setText(speed > 0 ? speed + " m" : "—");
        darkvisionLabel.setText(razza != null ? razza.getScurovisione().toString() : "—");

        hitDiceLabel.setText("—");

        int spellcastingModifier = calcolaModificatore(scoreOf("carisma"));
        int spellAttackBonus = spellcastingModifier + proficiency;
        int spellSaveDc = 8 + proficiency + spellcastingModifier;

        spellAttackBonusLabel.setText(formatBonus(spellAttackBonus));
        spellSaveDcLabel.setText(String.valueOf(spellSaveDc));
    }

    private void popolaSkills() {
        List<SkillBinding> skills = List.of(
                new SkillBinding("acrobazia", acrobaticsProfMark, acrobaticsBonusLabel, "destrezza"),
                new SkillBinding("addestrare animali", animalHandlingProfMark, animalHandlingBonusLabel, "saggezza"),
                new SkillBinding("arcano", arcanaProfMark, arcanaBonusLabel, "intelligenza"),
                new SkillBinding("atletica", athleticsProfMark, athleticsBonusLabel, "forza"),
                new SkillBinding("inganno", deceptionProfMark, deceptionBonusLabel, "carisma"),
                new SkillBinding("storia", historyProfMark, historyBonusLabel, "intelligenza"),
                new SkillBinding("intuizione", insightProfMark, insightBonusLabel, "saggezza"),
                new SkillBinding("intimidire", intimidationProfMark, intimidationBonusLabel, "carisma"),
                new SkillBinding("investigazione", investigationProfMark, investigationBonusLabel, "intelligenza"),
                new SkillBinding("medicina", medicineProfMark, medicineBonusLabel, "saggezza"),
                new SkillBinding("natura", natureProfMark, natureBonusLabel, "intelligenza"),
                new SkillBinding("percezione", perceptionProfMark, perceptionBonusLabel, "saggezza"),
                new SkillBinding("intrattenere", performanceProfMark, performanceBonusLabel, "carisma"),
                new SkillBinding("persuasione", persuasionProfMark, persuasionBonusLabel, "carisma"),
                new SkillBinding("religione", religionProfMark, religionBonusLabel, "intelligenza"),
                new SkillBinding("rapidità di mano", sleightOfHandProfMark, sleightOfHandBonusLabel, "destrezza"),
                new SkillBinding("furtività", stealthProfMark, stealthBonusLabel, "destrezza"),
                new SkillBinding("sopravvivenza", survivalProfMark, survivalBonusLabel, "saggezza")
        );

        int proficiency = calcolaBonusCompetenza();

        for (SkillBinding binding : skills) {
            int abilityModifier = calcolaModificatore(scoreOf(binding.abilityName()));
            int proficiencyLevel = getSkillProficiencyLevel(binding.skillName());
            int bonus = abilityModifier + (proficiency * proficiencyLevel);

            binding.mark().setText(proficiencyLevel > 0 ? "●" : "○");
            binding.bonus().setText(formatBonus(bonus));
        }

        int passive = 10 + calcolaModificatore(scoreOf("saggezza"));
        passive += proficiency * getSkillProficiencyLevel("percezione");
        passivePerceptionLabel.setText(String.valueOf(passive));
    }

    private void popolaMagie() {
        spellsTable.getItems().clear();

        for (Magia magia : nullSafe(scheda.getMagie())) {
            spellsTable.getItems().add(new SpellRow(
                    safe(magia.getNome()),
                    String.valueOf(magia.getLivello()),
                    Boolean.TRUE.equals(magia.getRituale()) ? "✓" : "",
                    safe(magia.getDescrizione())
            ));
        }
    }

    private void popolaProgressi() {
        classesTable.getItems().clear();
        classTraitsList.getItems().clear();
        classResourcesTable.getItems().clear();

        for (Progresso progresso : nullSafe(scheda.getProgressi())) {
            String className = progresso.getId().getNomeClasse();
            Sottoclasse sottoclasse = progresso.getSottoclasse();
            String subclassName = sottoclasse != null ? safe(sottoclasse.getId().getNomeSottoclasse()) : "—";

            classesTable.getItems().add(new ClassRow(className, subclassName, String.valueOf(progresso.getLivello())));

            addTraitsFromClass(progresso, progresso.getLivello());
            addResourcesFromClass(progresso);
        }
    }

    private void addTraitsFromClass(Progresso progresso, int livello) {
        for (AbilitazioneTratto abilitazione : nullSafe(progresso.getAbilitazioniTratti())) {
            TrattoClasse tratto = abilitazione.getTrattoClasse();
            if (tratto != null && tratto.getLivelloRichiesto() <= livello) {
                String descrizione = safe(tratto.getDescrizione());
                classTraitsList.getItems().add(
                        descrizione.equals("—") ? tratto.getId().getNome() : tratto.getId().getNome() + " — " + descrizione
                );
            }
        }
    }

    private void addResourcesFromClass(Progresso progresso) {
        for (AbilitazioneRisorsa abilitazione : nullSafe(progresso.getAbilitazioniRisorse())) {
            RisorsaClasse risorsa = abilitazione.getRisorsaClasse();
            if (risorsa != null) {
                classResourcesTable.getItems().add(new ResourceRow(risorsa.getId().getNomeRisorsa(), safe(risorsa.getRecupero())));
            }
        }
    }

    private void popolaInventario() {
        inventoryTable.getItems().clear();
        attacksTable.getItems().clear();

        for (RigaInventario riga : nullSafe(scheda.getInventario())) {
            inventoryTable.getItems().add(new ItemRow(
                    safe(riga.nome()),
                    String.valueOf(riga.quantita()),
                    riga.peso() + " kg",
                    safe(riga.descrizione())
            ));

            if (isWeaponType(riga)) {
                attacksTable.getItems().add(new AttackRow(
                        safe(riga.nome()), "—", riga.danno() + " / " + safe(riga.tipo())
                ));
            }
        }
    }

    private boolean isWeaponType(RigaInventario riga) {
        String normalized = normalize(riga.tipo());
        return normalized.contains("arma") || normalized.contains("weapon") || riga.danno() != null;
    }

    private void popolaInformazioniRazzaEBackground(Personaggio p) {
        Background background = p.getBackground();
        Razza razza = p.getRazza();

        backgroundDescriptionLabel.setText(background != null ? safe(background.getDescrizione()) : "—");
        raceDescriptionLabel.setText(razza != null ? safe(razza.getDescrizione()) : "—");
    }

    private int calcolaModificatore(int punteggio) {
        return (int) Math.floor((punteggio - 10) / 2.0);
    }

    private int calcolaBonusCompetenza() {
        int livelloTotale = nullSafe(scheda.getProgressi()).stream()
                .mapToInt(Progresso::getLivello)
                .sum();

        if (livelloTotale <= 0) {
            return 2;
        }

        return 2 + ((livelloTotale - 1) / 4);
    }

    private int scoreOf(String caratteristica) {
        Possesso possesso = caratteristicheByName().get(normalize(caratteristica));
        return possesso != null ? possesso.getPunteggio() : 10;
    }

    private Map<String, Possesso> caratteristicheByName() {
        Map<String, Possesso> byName = new HashMap<>();
        for (Possesso possesso : nullSafe(scheda.getCaratteristiche())) {
            byName.put(normalize(possesso.getId().getNomeCaratteristica()), possesso);
        }
        return byName;
    }

    private int getSkillProficiencyLevel(String skillName) {
        String wanted = normalize(skillName);
        for (Capacita capacita : nullSafe(scheda.getCapacita())) {
            if (normalize(capacita.getId().getNomeSkill()).equals(wanted)) {
                return proficiencyValue(capacita.getLivelloCapacita());
            }
        }
        return 0;
    }

    private static int proficiencyValue(String livello) {
        if (livello == null) return 0;
        return switch (livello.trim().toUpperCase(Locale.ROOT)) {
            case "E", "ESPERTO" -> 2;
            case "C", "COMPETENTE" -> 1;    
            default -> 0;
        };
    }

    private static String normalize(String value) {
        if (value == null) return "";
        return java.text.Normalizer.normalize(value, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .trim()
                .toLowerCase(Locale.ROOT);
    }

    private static String safe(String value) {
        return value == null || value.isBlank() ? "—" : value;
    }

    private static String formatBonus(int value) {
        return value >= 0 ? "+" + value : String.valueOf(value);
    }

    private static <T> Collection<T> nullSafe(Collection<T> collection) {
        return collection == null ? Collections.emptyList() : collection;
    }

    private record AttackRow(String name, String bonus, String damage) {}
    private record SpellRow(String name, String level, String ritual, String description) {}
    private record ClassRow(String className, String subclassName, String level) {}
    private record ResourceRow(String name, String recovery) {}
    private record ItemRow(String name, String quantity, String weight, String description) {}
    private record SkillBinding(String skillName, Label mark, Label bonus, String abilityName) {}
}