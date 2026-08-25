package it.unibo.dungeonsql.ui.controllers;

import it.unibo.dungeonsql.dtos.SchedaPersonaggio;
import it.unibo.dungeonsql.models.Magia;
import it.unibo.dungeonsql.models.Personaggio;
import it.unibo.dungeonsql.models.Possesso;
import it.unibo.dungeonsql.models.Progresso;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Controller della scheda personaggio.
 *
 * Il DTO SchedaPersonaggio continua ad essere la sorgente dei dati, come nel
 * vecchio controller. Il layout nuovo viene invece popolato direttamente sui
 * nodi definiti nel PersonaggioView.fxml.
 */
public class SchedaPersonaggioController extends ScrollPane {

    // -------------------------------------------------------------------------
    // Header
    // -------------------------------------------------------------------------

    @FXML private Label characterNameLabel;
    @FXML private Label backgroundLabel;
    @FXML private Label creatorLabel;
    @FXML private Label raceLabel;
    @FXML private Label alignmentLabel;
    @FXML private Label experienceLabel;

    // -------------------------------------------------------------------------
    // Caratteristiche
    // -------------------------------------------------------------------------

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

    // -------------------------------------------------------------------------
    // Skill
    // -------------------------------------------------------------------------

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

    // -------------------------------------------------------------------------
    // Combattimento
    // -------------------------------------------------------------------------

    @FXML private Label armorClassLabel;
    @FXML private Label initiativeLabel;
    @FXML private Label speedLabel;
    @FXML private Label currentHpLabel;
    @FXML private Label maxHpLabel;
    @FXML private Label hitDiceLabel;
    @FXML private Label sizeLabel;
    @FXML private Label spellAttackBonusLabel;
    @FXML private Label spellSaveDcLabel;

    // -------------------------------------------------------------------------
    // Tabelle / liste
    // -------------------------------------------------------------------------

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

    // -------------------------------------------------------------------------
    // Informazioni aggiuntive
    // -------------------------------------------------------------------------

    @FXML private Label backgroundDescriptionLabel;
    @FXML private Label raceDescriptionLabel;
    @FXML private Label baseSpeedLabel;
    @FXML private Label darkvisionLabel;

    private final SchedaPersonaggio scheda;

    public SchedaPersonaggioController(SchedaPersonaggio scheda) {
        this.scheda = scheda;

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/it/unibo/dungeonsql/ui/views/PersonaggioView.fxml")
        );
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento di PersonaggioView.fxml", e);
        }

        // Il costruttore viene invocato esplicitamente dall'applicazione;
        // quindi non dipendiamo da initialize() per avere i dati disponibili.
        if (scheda != null && scheda.getPersonaggio() != null) {
            popolaVista();
        }
    }

    /** Costruttore richiesto da eventuali loader/framework che non passano subito il DTO. */
    public SchedaPersonaggioController() {
        this.scheda = null;
    }

    @FXML
    private void initialize() {
        configuraTabelle();
    }

    // -------------------------------------------------------------------------
    // Setup tabella
    // -------------------------------------------------------------------------

    private void configuraTabelle() {
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

    // -------------------------------------------------------------------------
    // Popolamento principale
    // -------------------------------------------------------------------------

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
        characterNameLabel.setText(safe(p.getScheda() != null ? p.getScheda().getNome() : null));
        alignmentLabel.setText(safe(p.getAllineamento()));
        experienceLabel.setText(String.valueOf(p.getExpAccumulata()));

        creatorLabel.setText(
                safe(readNestedString(p.getScheda(), "getUsernameCreatore", "getCreatore", "getUsername"))
        );

        Object background = readObject(p, "getBackground", "getNomeBackground");
        Object race = readObject(p, "getRazza", "getNomeRazza");

        backgroundLabel.setText(nameOf(background));
        raceLabel.setText(nameOf(race));
    }

    private void popolaCaratteristiche() {
        Map<String, Possesso> byName = new HashMap<>();
        for (Possesso possesso : nullSafe(scheda.getCaratteristiche())) {
            String name = readPossessoName(possesso);
            if (name != null) {
                byName.put(normalize(name), possesso);
            }
        }

        int strength = scoreOf(byName, "forza", "strength", "for");
        int dexterity = scoreOf(byName, "destrezza", "dexterity", "des");
        int constitution = scoreOf(byName, "costituzione", "constitution", "cos");
        int intelligence = scoreOf(byName, "intelligenza", "intelligence", "int");
        int wisdom = scoreOf(byName, "saggezza", "wisdom", "sag");
        int charisma = scoreOf(byName, "carisma", "charisma", "car");

        int proficiency = calcolaBonusCompetenza();

        aggiornaCaratteristica(strengthScoreLabel, strengthModifierLabel, strengthSavingLabel,
                strength, getSavingBonus(byName, "forza", "strength", "for"), proficiency);
        aggiornaCaratteristica(dexterityScoreLabel, dexterityModifierLabel, dexteritySavingLabel,
                dexterity, getSavingBonus(byName, "destrezza", "dexterity", "des"), proficiency);
        aggiornaCaratteristica(constitutionScoreLabel, constitutionModifierLabel, constitutionSavingLabel,
                constitution, getSavingBonus(byName, "costituzione", "constitution", "cos"), proficiency);
        aggiornaCaratteristica(intelligenceScoreLabel, intelligenceModifierLabel, intelligenceSavingLabel,
                intelligence, getSavingBonus(byName, "intelligenza", "intelligence", "int"), proficiency);
        aggiornaCaratteristica(wisdomScoreLabel, wisdomModifierLabel, wisdomSavingLabel,
                wisdom, getSavingBonus(byName, "saggezza", "wisdom", "sag"), proficiency);
        aggiornaCaratteristica(charismaScoreLabel, charismaModifierLabel, charismaSavingLabel,
                charisma, getSavingBonus(byName, "carisma", "charisma", "car"), proficiency);

        proficiencyBonusLabel.setText(formatBonus(proficiency));
        combatProficiencyBonusLabel.setText(formatBonus(proficiency));
    }

    private void aggiornaCaratteristica(Label scoreLabel, Label modifierLabel, Label savingLabel,
                                        int score, boolean competent, int proficiency) {
        int modifier = calcolaModificatore(score);
        int saving = modifier + (competent ? proficiency : 0);

        scoreLabel.setText(String.valueOf(score));
        modifierLabel.setText(formatBonus(modifier));
        savingLabel.setText(formatBonus(saving));
    }

    private void popolaCombattimento(Personaggio p) {
        int proficiency = calcolaBonusCompetenza();
        int dexModifier = calcolaModificatore(scoreByName("destrezza", "dexterity", "des"));

        int armorClass = p.getScheda() != null ? p.getScheda().getCa() : 10;
        int currentHp = p.getHp();
        int maxHp = p.getScheda() != null ? p.getScheda().getMaxHp() : 0;

        armorClassLabel.setText(String.valueOf(armorClass));
        initiativeLabel.setText(formatBonus(dexModifier));
        currentHpLabel.setText(String.valueOf(currentHp));
        maxHpLabel.setText("/ " + maxHp);
        proficiencyBonusLabel.setText(formatBonus(proficiency));
        combatProficiencyBonusLabel.setText(formatBonus(proficiency));

        String size = p.getScheda() != null
                ? safe(p.getScheda().getTaglia())
                : "—";
        sizeLabel.setText(size);

        Object race = readObject(p, "getRazza", "getNomeRazza");
        int speed = integerValue(readObject(race, "getVelocitaBase", "getVelocitàBase"), 0);
        speedLabel.setText(speed > 0 ? speed + " m" : "—");
        baseSpeedLabel.setText(speed > 0 ? speed + " m" : "—");
        darkvisionLabel.setText(stringValue(readObject(race, "getScurovisione"), "—"));

        // Nel database non esiste un campo esplicito per i dadi vita.
        hitDiceLabel.setText("—");

        // Per il calcolo automatico delle magie usiamo Carisma come default.
        // In futuro è meglio ricavare la caratteristica di lancio dalla classe.
        int spellcastingModifier = calcolaModificatore(scoreByName("carisma", "charisma", "car"));
        int spellAttackBonus = spellcastingModifier + proficiency;
        int spellSaveDc = 8 + proficiency + spellcastingModifier;

        spellAttackBonusLabel.setText(formatBonus(spellAttackBonus));
        spellSaveDcLabel.setText(String.valueOf(spellSaveDc));
    }

    private void popolaSkills() {
        Map<String, SkillBinding> skills = Map.ofEntries(
                Map.entry("acrobazia", new SkillBinding("acrobazia", acrobaticsProfMark, acrobaticsBonusLabel, "destrezza", "dexterity", "des")),
                Map.entry("addestrare animali", new SkillBinding("addestrare animali", animalHandlingProfMark, animalHandlingBonusLabel, "saggezza", "wisdom", "sag")),
                Map.entry("arcano", new SkillBinding("arcano", arcanaProfMark, arcanaBonusLabel, "intelligenza", "intelligence", "int")),
                Map.entry("atletica", new SkillBinding("atletica", athleticsProfMark, athleticsBonusLabel, "forza", "strength", "for")),
                Map.entry("inganno", new SkillBinding("inganno", deceptionProfMark, deceptionBonusLabel, "carisma", "charisma", "car")),
                Map.entry("storia", new SkillBinding("storia", historyProfMark, historyBonusLabel, "intelligenza", "intelligence", "int")),
                Map.entry("intuizione", new SkillBinding("intuizione", insightProfMark, insightBonusLabel, "saggezza", "wisdom", "sag")),
                Map.entry("intimidire", new SkillBinding("intimidire", intimidationProfMark, intimidationBonusLabel, "carisma", "charisma", "car")),
                Map.entry("investigazione", new SkillBinding("investigazione", investigationProfMark, investigationBonusLabel, "intelligenza", "intelligence", "int")),
                Map.entry("medicina", new SkillBinding("medicina", medicineProfMark, medicineBonusLabel, "saggezza", "wisdom", "sag")),
                Map.entry("natura", new SkillBinding("natura", natureProfMark, natureBonusLabel, "intelligenza", "intelligence", "int")),
                Map.entry("percezione", new SkillBinding("percezione", perceptionProfMark, perceptionBonusLabel, "saggezza", "wisdom", "sag")),
                Map.entry("intrattenere", new SkillBinding("intrattenere", performanceProfMark, performanceBonusLabel, "carisma", "charisma", "car")),
                Map.entry("persuasione", new SkillBinding("persuasione", persuasionProfMark, persuasionBonusLabel, "carisma", "charisma", "car")),
                Map.entry("religione", new SkillBinding("religione", religionProfMark, religionBonusLabel, "intelligenza", "intelligence", "int")),
                Map.entry("rapidità di mano", new SkillBinding("rapidità di mano", sleightOfHandProfMark, sleightOfHandBonusLabel, "destrezza", "dexterity", "des")),
                Map.entry("furtività", new SkillBinding("furtività", stealthProfMark, stealthBonusLabel, "destrezza", "dexterity", "des")),
                Map.entry("sopravvivenza", new SkillBinding("sopravvivenza", survivalProfMark, survivalBonusLabel, "saggezza", "wisdom", "sag"))
        );

        for (SkillBinding binding : skills.values()) {
            int abilityScore = scoreByName(binding.abilityNames());
            int abilityModifier = calcolaModificatore(abilityScore);

            int proficiencyLevel = getSkillProficiencyLevel(binding.skillName());
            int bonus = abilityModifier + (calcolaBonusCompetenza() * proficiencyLevel);

            binding.mark().setText(proficiencyLevel > 0 ? "●" : "○");
            binding.bonus().setText(formatBonus(bonus));
        }

        int passive = 10 + calcolaModificatore(scoreByName("saggezza", "wisdom", "sag"));
        int perceptionProf = getSkillProficiencyLevel("percezione", "perception");
        passive += calcolaBonusCompetenza() * perceptionProf;
        passivePerceptionLabel.setText(String.valueOf(passive));
    }

    private void popolaMagie() {
        spellsTable.getItems().clear();

        for (Magia magia : nullSafe(scheda.getMagie())) {
            spellsTable.getItems().add(new SpellRow(
                    safe(magia.getNome()),
                    String.valueOf(magia.getLivello()),
                    booleanValue(magia.getRituale()) ? "✓" : "",
                    safe(magia.getDescrizione())
            ));
        }
    }

    private void popolaProgressi() {
        classesTable.getItems().clear();
        classTraitsList.getItems().clear();
        classResourcesTable.getItems().clear();

        for (Progresso progresso : nullSafe(scheda.getProgressi())) {
            Object id = readObject(progresso, "getId");
            String className = firstNonBlank(
                    stringValue(readObject(id, "getNomeClasse"), null),
                    stringValue(readObject(progresso, "getNomeClasse"), null),
                    "—"
            );

            Object subclass = readObject(progresso, "getSottoclasse", "getSubclass");
            String subclassName = nameOf(subclass);
            if ("—".equals(subclassName)) {
                subclassName = firstNonBlank(
                        stringValue(readObject(id, "getNomeSottoclasse"), null),
                        "—"
                );
            }

            classesTable.getItems().add(new ClassRow(
                    className,
                    subclassName,
                    String.valueOf(progresso.getLivello())
            ));

            // Se le entità generate espongono direttamente i tratti, li raccogliamo.
            Object classe = readObject(progresso, "getClasse");
            addTraitsFromClass(classe, progresso.getLivello());
            addResourcesFromClass(classe);
        }
    }

    private void popolaInventario() {
        inventoryTable.getItems().clear();
        attacksTable.getItems().clear();

        for (Object[] row : nullSafe(scheda.getInventario())) {
            String name = valueAt(row, 0);
            String quantity = valueAt(row, 1);
            String type = valueAt(row, 2);
            String weight = valueAt(row, 3);
            String description = valueAt(row, 4);

            inventoryTable.getItems().add(new ItemRow(name, quantity, weight, description));

            // Non esiste una tabella ATTACCO nel database: costruiamo l'elenco
            // degli attacchi a partire dagli oggetti che hanno caratteristiche arma.
            if (isWeaponType(type, row)) {
                String damage = valueAt(row, 5);
                String attackType = type == null || type.isBlank() ? "Arma" : type;
                attacksTable.getItems().add(new AttackRow(name, "—", damage + " / " + attackType));
            }
        }
    }

    private void popolaInformazioniRazzaEBackground(Personaggio p) {
        Object background = readObject(p, "getBackground", "getNomeBackground");
        Object race = readObject(p, "getRazza", "getNomeRazza");

        backgroundDescriptionLabel.setText(
                stringValue(readObject(background, "getDescrizione"), "—")
        );
        raceDescriptionLabel.setText(
                stringValue(readObject(race, "getDescrizione"), "—")
        );
    }

    // -------------------------------------------------------------------------
    // Calcoli D&D 5e
    // -------------------------------------------------------------------------

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

    private int scoreByName(String... names) {
        Map<String, Possesso> byName = new HashMap<>();
        for (Possesso possesso : nullSafe(scheda.getCaratteristiche())) {
            String name = readPossessoName(possesso);
            if (name != null) {
                byName.put(normalize(name), possesso);
            }
        }
        return scoreOf(byName, names);
    }

    private int scoreOf(Map<String, Possesso> byName, String... names) {
        for (String name : names) {
            Possesso p = byName.get(normalize(name));
            if (p != null) {
                return p.getPunteggio();
            }
        }
        return 10;
    }

    private boolean getSavingBonus(Map<String, Possesso> byName, String... names) {
        for (String name : names) {
            Possesso p = byName.get(normalize(name));
            if (p != null) {
                Object value = readObject(p, "getCompetenzaSalvezza", "isCompetenzaSalvezza");
                return booleanValue(value);
            }
        }
        return false;
    }

    private int getSkillProficiencyLevel(String... names) {
        String wanted = normalize(names[0]);

        // Il DTO vecchio non esponeva le capacità; il controllo viene fatto per
        // reflection per poter usare anche la versione nuova senza legare questo
        // controller a una specifica firma del getter.
        Object capabilities = readObject(scheda, "getCapacita", "getCapacità", "getSkills");
        if (capabilities instanceof Iterable<?> iterable) {
            for (Object capability : iterable) {
                Object id = readObject(capability, "getId");
                String skillName = firstNonBlank(
                        stringValue(readObject(id, "getNomeSkill"), null),
                        stringValue(readObject(capability, "getNomeSkill"), null),
                        ""
                );

                if (normalize(skillName).equals(wanted)) {
                    int level = integerValue(readObject(capability, "getLivelloCapacita", "getLivelloCapacità"), 0);
                    return Math.max(0, Math.min(level, 2));
                }
            }
        }

        return 0;
    }

    // -------------------------------------------------------------------------
    // Reflection helpers: permettono al controller di restare compatibile con
    // piccoli cambi di naming delle relazioni generate da Hibernate.
    // -------------------------------------------------------------------------

    private Object readObject(Object target, String... getters) {
        if (target == null) return null;

        for (String getter : getters) {
            try {
                Method method = target.getClass().getMethod(getter);
                return method.invoke(target);
            } catch (ReflectiveOperationException ignored) {
                // Proviamo il getter successivo.
            }
        }
        return null;
    }

    private String readNestedString(Object target, String... getters) {
        Object value = readObject(target, getters);
        return value == null ? null : String.valueOf(value);
    }

    private String readPossessoName(Possesso possesso) {
        Object id = readObject(possesso, "getId");
        return firstNonBlank(
                stringValue(readObject(id, "getNomeCaratteristica"), null),
                stringValue(readObject(possesso, "getNomeCaratteristica"), null),
                null
        );
    }

    private String nameOf(Object value) {
        if (value == null) return "—";
        if (value instanceof String s) return safe(s);

        String name = firstNonBlank(
                stringValue(readObject(value, "getNome"), null),
                stringValue(readObject(value, "getNomeBackground"), null),
                stringValue(readObject(value, "getNomeRazza"), null),
                stringValue(readObject(value, "getNomeClasse"), null),
                "—"
        );
        return safe(name);
    }

    private void addTraitsFromClass(Object classe, int livello) {
        if (classe == null) return;

        Object traits = readObject(classe, "getTratti", "getTrattiClasse", "getTrattiClassi");
        if (!(traits instanceof Iterable<?> iterable)) return;

        for (Object trait : iterable) {
            int requiredLevel = integerValue(readObject(trait, "getLivelloRichiesto", "getLivelloRichiestoTrait"), 0);
            if (requiredLevel <= livello) {
                String name = nameOf(trait);
                String description = stringValue(readObject(trait, "getDescrizione"), "");
                classTraitsList.getItems().add(
                        description.isBlank() ? name : name + " — " + description
                );
            }
        }
    }

    private void addResourcesFromClass(Object classe) {
        if (classe == null) return;

        Object resources = readObject(classe, "getRisorse", "getRisorseClasse", "getRisorsaClasse");
        if (!(resources instanceof Iterable<?> iterable)) return;

        for (Object resource : iterable) {
            classResourcesTable.getItems().add(new ResourceRow(
                    nameOf(resource),
                    stringValue(readObject(resource, "getRecupero"), "—")
            ));
        }
    }

    private boolean isWeaponType(String type, Object[] row) {
        if (type != null) {
            String normalized = normalize(type);
            if (normalized.contains("arma") || normalized.contains("weapon")) {
                return true;
            }
        }

        // Se l'array contiene un campo danno valorizzato, consideriamolo un'arma.
        return row != null && row.length > 5 && row[5] != null && !String.valueOf(row[5]).isBlank();
    }

    // -------------------------------------------------------------------------
    // Utility
    // -------------------------------------------------------------------------

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

    private static boolean booleanValue(Object value) {
        if (value == null) return false;
        if (value instanceof Boolean b) return b;
        String normalized = normalize(String.valueOf(value));
        return normalized.equals("true") || normalized.equals("t") || normalized.equals("1")
                || normalized.equals("si") || normalized.equals("s") || normalized.equals("yes")
                || normalized.equals("y") || normalized.equals("proficiente");
    }

    private static int integerValue(Object value, int defaultValue) {
        if (value == null) return defaultValue;
        if (value instanceof Number n) return n.intValue();
        try {
            return Integer.parseInt(String.valueOf(value).trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private static String stringValue(Object value, String defaultValue) {
        return value == null ? defaultValue : String.valueOf(value);
    }

    @SafeVarargs
    private static <T> String firstNonBlank(String... values) {
        for (String value : values) {
            if (value != null && !value.isBlank()) return value;
        }
        return null;
    }

    private static String valueAt(Object[] row, int index) {
        if (row == null || index < 0 || index >= row.length || row[index] == null) return "—";
        return String.valueOf(row[index]);
    }

    private static <T> Collection<T> nullSafe(Collection<T> collection) {
        return collection == null ? Collections.emptyList() : collection;
    }

    // -------------------------------------------------------------------------
    // Riga View
    // -------------------------------------------------------------------------

    private record AttackRow(String name, String bonus, String damage) {}
    private record SpellRow(String name, String level, String ritual, String description) {}
    private record ClassRow(String className, String subclassName, String level) {}
    private record ResourceRow(String name, String recovery) {}
    private record ItemRow(String name, String quantity, String weight, String description) {}
    private record SkillBinding(String skillName, Label mark, Label bonus, String... abilityNames) {}
}