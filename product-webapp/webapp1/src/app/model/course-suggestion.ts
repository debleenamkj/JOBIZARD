export class CourseSuggestion {
    suggestionId:number;
    skillType:string;
    sourceUrl:string;

    constructor(suggestionId:number, skillType:string, sourceUrl:string){
        this.suggestionId = suggestionId;
        this.skillType = skillType;
        this.sourceUrl = sourceUrl;
    }
}
