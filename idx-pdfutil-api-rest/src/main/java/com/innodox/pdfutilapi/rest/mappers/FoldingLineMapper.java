package com.innodox.pdfutilapi.rest.mappers;

import com.innodox.pdfutilapi.models.folding.FoldingLine;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FoldingLineMapper {

    FoldingLine fromRestDtoToModel(com.innodox.pdfutilapi.rest.model.generated.FoldingLine foldingLineRestDto);

}
