package com.fastcode.timesheetapp1.application.core.timesheetstatus;

import com.fastcode.timesheetapp1.application.core.timesheetstatus.dto.CreateTimesheetstatusInput;
import com.fastcode.timesheetapp1.application.core.timesheetstatus.dto.FindTimesheetstatusByIdOutput;
import com.fastcode.timesheetapp1.application.core.timesheetstatus.dto.UpdateTimesheetstatusInput;
import com.fastcode.timesheetapp1.commons.logging.LoggingHelper;
import com.fastcode.timesheetapp1.commons.search.SearchCriteria;
import com.fastcode.timesheetapp1.commons.search.SearchFields;
import com.fastcode.timesheetapp1.domain.core.timesheetstatus.ITimesheetstatusRepository;
import com.fastcode.timesheetapp1.domain.core.timesheetstatus.QTimesheetstatusEntity;
import com.fastcode.timesheetapp1.domain.core.timesheetstatus.TimesheetstatusEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class TimesheetstatusAppServiceTest {

    @InjectMocks
    @Spy
    protected TimesheetstatusAppService _appService;

    @Mock
    protected ITimesheetstatusRepository _timesheetstatusRepository;

    @Mock
    protected ITimesheetstatusMapper _mapper;

    @Mock
    protected Logger loggerMock;

    @Mock
    protected LoggingHelper logHelper;

    protected static Long ID = 15L;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(_appService);
        when(logHelper.getLogger()).thenReturn(loggerMock);
        doNothing().when(loggerMock).error(anyString());
    }

    @Test
    public void findTimesheetstatusById_IdIsNotNullAndIdDoesNotExist_ReturnNull() {
        Optional<TimesheetstatusEntity> nullOptional = Optional.ofNullable(null);
        Mockito.when(_timesheetstatusRepository.findById(anyLong())).thenReturn(nullOptional);
        Assertions.assertThat(_appService.findById(ID)).isEqualTo(null);
    }

    @Test
    public void findTimesheetstatusById_IdIsNotNullAndIdExists_ReturnTimesheetstatus() {
        TimesheetstatusEntity timesheetstatus = mock(TimesheetstatusEntity.class);
        Optional<TimesheetstatusEntity> timesheetstatusOptional = Optional.of((TimesheetstatusEntity) timesheetstatus);
        Mockito.when(_timesheetstatusRepository.findById(anyLong())).thenReturn(timesheetstatusOptional);

        Assertions
            .assertThat(_appService.findById(ID))
            .isEqualTo(_mapper.timesheetstatusEntityToFindTimesheetstatusByIdOutput(timesheetstatus));
    }

    @Test
    public void createTimesheetstatus_TimesheetstatusIsNotNullAndTimesheetstatusDoesNotExist_StoreTimesheetstatus() {
        TimesheetstatusEntity timesheetstatusEntity = mock(TimesheetstatusEntity.class);
        CreateTimesheetstatusInput timesheetstatusInput = new CreateTimesheetstatusInput();

        Mockito
            .when(_mapper.createTimesheetstatusInputToTimesheetstatusEntity(any(CreateTimesheetstatusInput.class)))
            .thenReturn(timesheetstatusEntity);
        Mockito
            .when(_timesheetstatusRepository.save(any(TimesheetstatusEntity.class)))
            .thenReturn(timesheetstatusEntity);

        Assertions
            .assertThat(_appService.create(timesheetstatusInput))
            .isEqualTo(_mapper.timesheetstatusEntityToCreateTimesheetstatusOutput(timesheetstatusEntity));
    }

    @Test
    public void updateTimesheetstatus_TimesheetstatusIdIsNotNullAndIdExists_ReturnUpdatedTimesheetstatus() {
        TimesheetstatusEntity timesheetstatusEntity = mock(TimesheetstatusEntity.class);
        UpdateTimesheetstatusInput timesheetstatus = mock(UpdateTimesheetstatusInput.class);

        Optional<TimesheetstatusEntity> timesheetstatusOptional = Optional.of(
            (TimesheetstatusEntity) timesheetstatusEntity
        );
        Mockito.when(_timesheetstatusRepository.findById(anyLong())).thenReturn(timesheetstatusOptional);

        Mockito
            .when(_mapper.updateTimesheetstatusInputToTimesheetstatusEntity(any(UpdateTimesheetstatusInput.class)))
            .thenReturn(timesheetstatusEntity);
        Mockito
            .when(_timesheetstatusRepository.save(any(TimesheetstatusEntity.class)))
            .thenReturn(timesheetstatusEntity);
        Assertions
            .assertThat(_appService.update(ID, timesheetstatus))
            .isEqualTo(_mapper.timesheetstatusEntityToUpdateTimesheetstatusOutput(timesheetstatusEntity));
    }

    @Test
    public void deleteTimesheetstatus_TimesheetstatusIsNotNullAndTimesheetstatusExists_TimesheetstatusRemoved() {
        TimesheetstatusEntity timesheetstatus = mock(TimesheetstatusEntity.class);
        Optional<TimesheetstatusEntity> timesheetstatusOptional = Optional.of((TimesheetstatusEntity) timesheetstatus);
        Mockito.when(_timesheetstatusRepository.findById(anyLong())).thenReturn(timesheetstatusOptional);

        _appService.delete(ID);
        verify(_timesheetstatusRepository).delete(timesheetstatus);
    }

    @Test
    public void find_ListIsEmpty_ReturnList() throws Exception {
        List<TimesheetstatusEntity> list = new ArrayList<>();
        Page<TimesheetstatusEntity> foundPage = new PageImpl(list);
        Pageable pageable = mock(Pageable.class);
        List<FindTimesheetstatusByIdOutput> output = new ArrayList<>();
        SearchCriteria search = new SearchCriteria();

        Mockito.when(_appService.search(any(SearchCriteria.class))).thenReturn(new BooleanBuilder());
        Mockito
            .when(_timesheetstatusRepository.findAll(any(Predicate.class), any(Pageable.class)))
            .thenReturn(foundPage);
        Assertions.assertThat(_appService.find(search, pageable)).isEqualTo(output);
    }

    @Test
    public void find_ListIsNotEmpty_ReturnList() throws Exception {
        List<TimesheetstatusEntity> list = new ArrayList<>();
        TimesheetstatusEntity timesheetstatus = mock(TimesheetstatusEntity.class);
        list.add(timesheetstatus);
        Page<TimesheetstatusEntity> foundPage = new PageImpl(list);
        Pageable pageable = mock(Pageable.class);
        List<FindTimesheetstatusByIdOutput> output = new ArrayList<>();
        SearchCriteria search = new SearchCriteria();

        output.add(_mapper.timesheetstatusEntityToFindTimesheetstatusByIdOutput(timesheetstatus));

        Mockito.when(_appService.search(any(SearchCriteria.class))).thenReturn(new BooleanBuilder());
        Mockito
            .when(_timesheetstatusRepository.findAll(any(Predicate.class), any(Pageable.class)))
            .thenReturn(foundPage);
        Assertions.assertThat(_appService.find(search, pageable)).isEqualTo(output);
    }

    @Test
    public void searchKeyValuePair_PropertyExists_ReturnBooleanBuilder() {
        QTimesheetstatusEntity timesheetstatus = QTimesheetstatusEntity.timesheetstatusEntity;
        SearchFields searchFields = new SearchFields();
        searchFields.setOperator("equals");
        searchFields.setSearchValue("xyz");
        Map<String, SearchFields> map = new HashMap<>();
        map.put("statusname", searchFields);
        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("xyz", String.valueOf(ID));
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(timesheetstatus.statusname.eq("xyz"));
        Assertions.assertThat(_appService.searchKeyValuePair(timesheetstatus, map, searchMap)).isEqualTo(builder);
    }

    @Test(expected = Exception.class)
    public void checkProperties_PropertyDoesNotExist_ThrowException() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("xyz");
        _appService.checkProperties(list);
    }

    @Test
    public void checkProperties_PropertyExists_ReturnNothing() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("statusname");
        _appService.checkProperties(list);
    }

    @Test
    public void search_SearchIsNotNullAndSearchContainsCaseThree_ReturnBooleanBuilder() throws Exception {
        Map<String, SearchFields> map = new HashMap<>();
        QTimesheetstatusEntity timesheetstatus = QTimesheetstatusEntity.timesheetstatusEntity;
        List<SearchFields> fieldsList = new ArrayList<>();
        SearchFields fields = new SearchFields();
        SearchCriteria search = new SearchCriteria();
        search.setType(3);
        search.setValue("xyz");
        search.setOperator("equals");
        fields.setFieldName("statusname");
        fields.setOperator("equals");
        fields.setSearchValue("xyz");
        fieldsList.add(fields);
        search.setFields(fieldsList);
        BooleanBuilder builder = new BooleanBuilder();
        builder.or(timesheetstatus.statusname.eq("xyz"));
        Mockito.doNothing().when(_appService).checkProperties(any(List.class));
        Mockito
            .doReturn(builder)
            .when(_appService)
            .searchKeyValuePair(any(QTimesheetstatusEntity.class), any(HashMap.class), any(HashMap.class));

        Assertions.assertThat(_appService.search(search)).isEqualTo(builder);
    }

    @Test
    public void search_StringIsNull_ReturnNull() throws Exception {
        Assertions.assertThat(_appService.search(null)).isEqualTo(null);
    }

    @Test
    public void ParsetimesheetsJoinColumn_KeysStringIsNotEmptyAndKeyValuePairDoesNotExist_ReturnNull() {
        Map<String, String> joinColumnMap = new HashMap<String, String>();
        String keyString = "15";
        joinColumnMap.put("timesheetstatusid", keyString);
        Assertions.assertThat(_appService.parseTimesheetsJoinColumn(keyString)).isEqualTo(joinColumnMap);
    }
}
