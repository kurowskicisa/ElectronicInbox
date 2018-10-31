package com.ctk.dao;

import com.ctk.freemarker.ModelGeneratorTemplate;
import com.ctk.freemarker.TemplateProvider;
import com.ctk.model.ElectronicInbox;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RequestScoped
public class ElectrinicInboxFilter {


    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private ModelGeneratorTemplate modelGeneratorTemplate;

    @Inject
    private ElectronicinboxLoadFromFile electronicinboxLoadFromFile;

    @Inject
    private ElectronicInboxDao electronicInboxDao;

    @Inject
    private ElectronicInbox electronicInbox;


    List<ElectronicInbox> filterElectronicInbox;

    public List<ElectronicInbox> filterElectronicInbox(String choiceName, String choiceAddress, String choicePlace) {
        filterElectronicInbox = electronicInboxDao.getList();

        filterElectronicInboxByName(choiceName);

        return filterElectronicInbox.stream()
                .sorted(Comparator.comparing(ElectronicInbox::getName))
                .collect(Collectors.toList());
    }


    private void filterElectronicInboxByName(String choiceName) {
        if(!choiceName.isEmpty()) {
            filterElectronicInbox = filterElectronicInbox.stream()
                    .filter(eib -> electronicInbox.getName().equals(choiceName))
                    .collect(Collectors.toList());
        }
    }
}
